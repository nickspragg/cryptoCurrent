val kotlin_version: String by extra
plugins {
    id(AppBuildPlugins.androidLibrary)
    id(AppBuildPlugins.kotlinAndroid)
    id(AppBuildPlugins.kotlinAndroidExtensions)
    id(AppBuildPlugins.kotlinKapt)
}
apply {
    plugin("kotlin-android")
    plugin("kotlin-android-extensions")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    api(CoreLibraries.kotlinStdLib)
    api(CoreLibraries.appCompat)
    api(CoreLibraries.ktxCore)
    api(UILibraries.constraintLayout)
    api(CoreLibraries.rxJava)
    api(CoreLibraries.rxKotlin)
    api(CoreLibraries.rxAndroid)

    implementation(CoreLibraries.dagger2Android)
    implementation(CoreLibraries.dagger2AndroidSupport)
    kapt(CoreLibraries.dagger2AnnotationProcessor)
    kapt(CoreLibraries.dagger2Compiler)

    testImplementation (TestLibraries.junit4)
    androidTestImplementation (TestLibraries.testRunner)
    androidTestImplementation (TestLibraries.espresso)
}
repositories {
    mavenCentral()
}
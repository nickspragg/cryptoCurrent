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

    implementation(project(":core"))

    implementation(UILibraries.materialComponents)
    implementation(UILibraries.constraintLayout)
    implementation(UILibraries.recyclerView)
    implementation(IOLibraries.commonsIo)
    implementation(IOLibraries.gson)

    kapt(CoreLibraries.dagger2AnnotationProcessor)
    kapt(CoreLibraries.dagger2Compiler)

    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.mockitoKotlin)
    androidTestImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}
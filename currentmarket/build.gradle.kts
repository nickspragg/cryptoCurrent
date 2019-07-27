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

    implementation(UILibraries.mpAndroidChart)
    implementation(NetworkLibraries.retrofit2)
    implementation(NetworkLibraries.retrofitRxJavaAdapter)
    implementation(NetworkLibraries.gsonConverter)

    kapt(CoreLibraries.dagger2AnnotationProcessor)
    kapt(CoreLibraries.dagger2Compiler)

    testImplementation (TestLibraries.junit4)
    androidTestImplementation (TestLibraries.testRunner)
    androidTestImplementation (TestLibraries.espresso)
}
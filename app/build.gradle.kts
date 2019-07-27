plugins {
    id(AppBuildPlugins.androidApplication)
    id(AppBuildPlugins.kotlinAndroid)
    id(AppBuildPlugins.kotlinAndroidExtensions)
    id(AppBuildPlugins.kotlinKapt)
    id(AppBuildPlugins.fabric)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.nickspragg.cryptocurrent"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
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
    implementation(CoreLibraries.appCompat)
    implementation(CoreLibraries.firebaseCore)
    implementation(CoreLibraries.crashlytics)
    implementation(CoreLibraries.timberLog)
    implementation(UILibraries.constraintLayout)

    implementation(CoreLibraries.dagger2Android)
    implementation(CoreLibraries.dagger2AndroidSupport)
    kapt(CoreLibraries.dagger2AnnotationProcessor)
    kapt(CoreLibraries.dagger2Compiler)

    testImplementation (TestLibraries.junit4)
    androidTestImplementation (TestLibraries.testRunner)
    androidTestImplementation (TestLibraries.espresso)
}


apply(mapOf("plugin" to AppBuildPlugins.googleService))


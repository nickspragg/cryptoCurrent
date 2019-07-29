const val kotlinVersion = "1.3.41"

object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "3.4.2"
        const val googleServiceVersion = "4.2.0"
        const val fabricVersion = "1.30.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val googleServiceDependency = "com.google.gms:google-services:${Versions.googleServiceVersion}"
    const val fabricPlugin = "io.fabric.tools:gradle:${Versions.fabricVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

object AppBuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val googleService = "com.google.gms.google-services"
    const val fabric = "io.fabric"
}

object AndroidSdk {
    const val min = 21
    const val compile = 28
    const val target = compile
}

object CoreLibraries {
    private object Versions {
        const val jetpack = "1.1.0-rc01"
        const val ktx = "1.1.0-rc02"
        const val dagger2 = "2.23.2"
        const val rxJava = "2.2.10"
        const val rxKotlin = "2.3.0"
        const val rxAndroid = "2.1.1"
        const val timber = "4.7.1"
        const val firebaseCore = "17.0.1"
        const val crashlytics = "2.10.1"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    // Rx core dependencies
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    // Dagger core dependencies
    const val dagger2Compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
    // Dagger Android dependencies
    const val dagger2 = "com.google.dagger:dagger:${Versions.dagger2}"
    const val dagger2Android = "com.google.dagger:dagger-android:${Versions.dagger2}"
    const val dagger2AndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger2}"
    const val dagger2AnnotationProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger2}"

    const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
    const val timberLog = "com.jakewharton.timber:timber:${Versions.timber}"
}

object UILibraries {
    private object Versions {
        const val constraintLayout = "1.1.3" // 2.0.0-beta2
        const val mpAndroidChart = "v3.1.0"
        const val materialComponents = "1.0.0" // 1.1.0-alpha08
        const val shimmerLayout = "0.5.0"
    }

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val mpAndroidChart = "com.github.PhilJay:MPAndroidChart:${Versions.mpAndroidChart}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    const val shimmerLayout = "com.facebook.shimmer:shimmer:${Versions.shimmerLayout}"
}

object NetworkLibraries {
    private object Versions {
        const val okHttp = "3.10.0"
        const val logger = "3.8.1"
        const val retrofit2 = "2.6.0"
        const val retrofitRxJavaAdapter = "2.6.0"
        const val gsonConverter = "2.6.0"
        const val gson = "2.8.5"
    }

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val logger = "com.squareup.okhttp3:logging-interceptor:${Versions.logger}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitRxJavaAdapter}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object CrashReportLibraries {
    private object Versions {
        const val timber = "4.7.1"
        const val firebaseCrashlytics = "16.2.1"
    }

    const val firebaseCrashlytics = "com.google.firebase:firebase-crash:${Versions.firebaseCrashlytics}"
    const val timberLog = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val mockito = "3.0.0"
        const val mockitoKotlin = "1.6.0"
        const val testRunner = "1.2.0"
        const val espresso = "3.2.0"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
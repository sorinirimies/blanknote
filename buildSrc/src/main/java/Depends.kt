object Depends {

    object BuildPlugins {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.gradlePluginVersion}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object Android {
        const val supportAnnotations = "androidx.annotation:annotation:${Versions.androidSupportVersion}"
        const val supportAppCompat = "androidx.appcompat:appcompat:${Versions.androidSupportVersion}"
        const val lifecycleExt =
                "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
        const val lifecycleRuntime =
                "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleVersion}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.ktxVersion}"
        const val ktxFragment = "androidx.fragment:fragment-ktx:${Versions.ktxVersion}"
        const val material = " com.google.android.material:material:${Versions.androidMaterialVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    }

    object CI {
        const val appCenterAnalytics = "com.microsoft.appcenter:appcenter-analytics:${Versions.appCenterVersion}"
        const val appCenterCrashes = "com.microsoft.appcenter:appcenter-crashes:${Versions.appCenterVersion}"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomCoroutines = "androidx.room:room-coroutines:${Versions.roomVersion}"
        const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"
    }

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    }

    object Firebase {
        const val fireStore = "com.google.firebase:firebase-firestore:${Versions.fireStoreVersion}"
        const val fireAuth = "com.google.firebase:firebase-auth:${Versions.fireaAuthVersion}"
    }

    object UserAuth {
        const val fbLogin = "com.facebook.android:facebook-android-sdk:${Versions.facebookVersion}"
        const val twitterLogin = "com.twitter.sdk.android:twitter:${Versions.twitterVersion}"
    }

    object Network {
        const val moshi = "com.squareup.moshi:moshi:1.8.0"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
        const val retrofit2CoroutinesAdapter =
                "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit2CorutinesAdapter}"
    }

    object TestLibraries {
        const val jUnit = "junit:junit:${Versions.junitVersion}"
        const val jUnitRunner = "androidx.test:runner:${Versions.junitRunnerVersion}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
        const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
    }

}
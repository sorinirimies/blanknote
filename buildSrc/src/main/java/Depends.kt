object Depends {

    object BuildPlugins {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.Android.gradlePlugin}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val googleServicesPlugin = "com.google.gms:google-services:${Versions.servicesVersion}"
    }

    object Android {
        const val supportAnnotations = "androidx.annotation:annotation:${Versions.AndroidX.support}"
        const val supportAppCompat = "androidx.appcompat:appcompat:${Versions
                .AndroidX.appCompat}"
        const val lifecycleExt =
                "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifeCycle}"
        const val lifecycleRuntime =
                "androidx.lifecycle:lifecycle-runtime:${Versions.AndroidX.lifeCycle}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.AndroidX.ktxVersion}"
        const val ktxFragment = "androidx.fragment:fragment-ktx:${Versions.AndroidX.ktxVersion}"
        const val material = " com.google.android.material:material:${Versions.AndroidX.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions
                .AndroidX.constraintLayout}"
    }

    object CI {
        const val appCenterAnalytics = "com.microsoft.appcenter:appcenter-analytics:${Versions.appCenterVersion}"
        const val appCenterCrashes = "com.microsoft.appcenter:appcenter-crashes:${Versions.appCenterVersion}"
    }

    object Image {
        const val roundImage = "com.makeramen:roundedimageview:${Versions.roundedImageVersion}"
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
        const val roomCoroutines = "androidx.room:room-coroutines:${Versions.AndroidX.roomCoroutines}"
        const val roomTest = "androidx.room:room-testing:${Versions.AndroidX.room}"
    }

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    }

    object PlayServices {
        const val authPlay = "com.google.android.gms:play-services-auth:${Versions.playServAuthVersion}"
    }

    object Firebase {
        const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCoreVersion}"
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
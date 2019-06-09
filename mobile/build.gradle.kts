import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("com.google.gms.google-services")
}

val versionName = Versions.Android.appVersionName

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        applicationId = Config.Application.applicationId
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        val booleanType = "Boolean"

        getByName("debug") {
            buildConfigField(booleanType, Config.BuildFurniture.ENABLE_CRASHLYTICS, false.toString())
            isDebuggable = true
        }

        create("develop") {
            buildConfigField(booleanType, Config.BuildFurniture.ENABLE_CRASHLYTICS, true.toString())
            isShrinkResources = false
            isMinifyEnabled = false
            isUseProguard = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            buildConfigField(booleanType, Config.BuildFurniture.ENABLE_CRASHLYTICS, true.toString())
            isShrinkResources = true
            isMinifyEnabled = true
            isUseProguard = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        applicationVariants.all(object : Action<ApplicationVariant> {
            override fun execute(variant: ApplicationVariant) {
                variant.outputs.all(object : Action<BaseVariantOutput> {
                    override fun execute(output: BaseVariantOutput) {
                        val outputImpl = output as BaseVariantOutputImpl
                        val fileName = "${variant.name.capitalize()}-$versionName.apk"
                        outputImpl.outputFileName = fileName
                    }
                })
            }
        })
    }

    androidExtensions { isExperimental = true }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    /*Kotlin*/
    implementation(Depends.Kotlin.kotlinStdLib)
    implementation(Depends.Kotlin.coroutines)

    /*Android X*/
    implementation(Depends.Android.constraintLayout)
    implementation(Depends.Android.supportAppCompat)
    implementation(Depends.Android.supportAnnotations)
    implementation("com.android.support:design:28.0.0") //TODO this needs to be migrated
    implementation("com.android.support:preference-v7:28.0.0")
    implementation(Depends.Android.lifecycleExt)
    implementation(Depends.Android.lifecycleRuntime)
    implementation(Depends.Android.ktxCore)
    implementation(Depends.Android.ktxFragment)

    /*C.I.*/
    implementation(Depends.CI.appCenterAnalytics)
    implementation(Depends.CI.appCenterCrashes)

    /*Firestore*/
    implementation(Depends.Firebase.fireStore)
    implementation(Depends.Firebase.firebaseCore)
    /*Network*/
    implementation(Depends.Network.retrofit2)
    implementation(Depends.Network.retrofit2CoroutinesAdapter)
    implementation(Depends.Network.moshi)

    /*User auth*/
    implementation(Depends.Firebase.fireAuth)
    implementation(Depends.PlayServices.authPlay)
    implementation(Depends.UserAuth.fbLogin)
    implementation(Depends.UserAuth.twitterLogin) {
        isTransitive = true
    }

    /*Room*/
    implementation(Depends.Room.roomRuntime)
    kapt(Depends.Room.roomCompiler)
    implementation(Depends.Room.roomCoroutines)

    /*Image loading*/
    implementation(Depends.Image.picasso)
    implementation(Depends.Image.roundImage)

    /*Logs*/
    implementation("com.jakewharton.timber:timber:4.7.1")
    /*Utils*/
    implementation("com.sorinirimies:kotlin-ext:1.0.0")

    /*Tests*/
    androidTestImplementation(Depends.TestLibraries.jUnitRunner)
    androidTestImplementation(Depends.TestLibraries.espressoCore)
    testImplementation(Depends.TestLibraries.jUnit)
    testImplementation(Depends.Room.roomTest)
}


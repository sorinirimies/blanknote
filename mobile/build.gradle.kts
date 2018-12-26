import org.jetbrains.kotlin.config.KotlinCompilerVersion
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.kotlin.dsl.*
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-android-extensions")
}

val versionName = Versions.Android.appVersionName

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
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
    implementation(Depends.Android.lifecycleExt)
    implementation(Depends.Android.lifecycleRuntime)
    implementation(Depends.Android.ktxCore)
    implementation(Depends.Android.ktxFragment)

    /*C.I.*/
    implementation(Depends.CI.appCenterAnalytics)
    implementation(Depends.CI.appCenterCrashes)

    /*Firestore*/
    implementation(Depends.Firebase.fireStore)

    /*Network*/
    implementation(Depends.Network.retrofit2)
    implementation(Depends.Network.retrofit2CoroutinesAdapter)
    implementation(Depends.Network.moshi)

    /*User auth*/
    implementation(Depends.Firebase.fireAuth)
    implementation(Depends.UserAuth.fbLogin)
    implementation(Depends.UserAuth.twitterLogin) {
        isTransitive = true
    }

    /*Room*/
    implementation(Depends.Room.roomRuntime)
    implementation(Depends.Room.roomCompiler)
    implementation(Depends.Room.roomCoroutines)

    /*Utils*/
    implementation("com.sorinirimies:kotlin-ext:1.0.0")

    /*Jwt*/
    implementation("io.jsonwebtoken:jjwt-api:0.10.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.5")
    runtimeOnly("io.jsonwebtoken:jjwt-orgjson:0.10.5") {
        exclude(group = "org.json", module = "json") //provided by Android natively
    }

    /*Tests*/
    androidTestImplementation(Depends.TestLibraries.jUnitRunner)
    androidTestImplementation(Depends.TestLibraries.espressoCore)
    testImplementation(Depends.TestLibraries.jUnit)
    testImplementation(Depends.Room.roomTest)

}

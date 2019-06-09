object Versions {

    object Android {
        const val compileSdkVersion = 28
        const val minSdkVersion = 22
        const val targetSdkVersion = 28
        const val appVersionCode = 1
        const val appVersionName = "1.0"
        const val gradlePlugin = "3.4.1"
    }

    object AndroidX{
        const val appCompat = "1.0.2"
        const val archCore = "2.0.1"
        const val constraintLayout = "1.1.3"
        const val core = "1.0.1"
        const val navigation = "2.0.0"
        const val liveData = "2.0.0"
        const val lifeCycle = "2.0.0"
        const val activityX = "1.0.0"
        const val support = "1.0.0"
        const val material = "1.0.0"
        const val ktxVersion = "1.0.0"
        const val room = "2.1.0-rc01"
        const val roomCoroutines = "2.1.0-alpha04"
    }

    /*Kotlin*/
    const val kotlinVersion = "1.3.31"
    const val kotlinCoroutines = "1.2.1"

    const val servicesVersion = "4.0.1"


    /*Firebase*/
    const val firebaseCoreVersion = "16.0.9"
    const val fireaAuthVersion = "17.0.0"
    const val fireStoreVersion = "19.0.1"

    /*Room*/

    /*C.I.*/
    const val appCenterVersion = "1.10.0"

    /*User Auth*/
    const val facebookVersion = "[4,5)"
    const val twitterVersion = "3.3.0@aar"

    const val playServAuthVersion = "16.0.1"

    /* Images*/
    const val roundedImageVersion = "2.3.0"
    const val picasso = "2.71828"

    /*Network*/
    const val retrofit2Version = "2.4.0"
    const val retrofit2CorutinesAdapter = "0.9.2"

    /*Testing*/
    const val junitVersion = "4.12"
    const val junitRunnerVersion = Versions.AndroidX.support
    const val espressoCoreVersion = "3.1.0"
    const val mockkVersion = "1.8.12"
}

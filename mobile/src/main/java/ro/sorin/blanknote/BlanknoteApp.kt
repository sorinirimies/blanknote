package ro.sorin.blanknote

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class BlanknoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(
            this,
            this.resources.getString(R.string.app_center_id),
            Analytics::class.java,
            Crashes::class.java
        )
    }

}
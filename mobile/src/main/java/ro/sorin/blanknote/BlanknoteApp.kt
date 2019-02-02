package ro.sorin.blanknote

import android.app.Application
import androidx.room.Room
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import ro.sorin.blanknote.db.NotesDb
import ro.sorin.blanknote.db.ShoppingListDb
import timber.log.Timber

class BlanknoteApp : Application() {

    companion object {
        lateinit var notesDb: NotesDb
        lateinit var shoppingListDb: ShoppingListDb
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        FirebaseApp.initializeApp(applicationContext)
        AppEventsLogger.activateApp(this)
        FacebookSdk.sdkInitialize(applicationContext)
        AppCenter.start(
                this,
                this.resources.getString(R.string.app_center_id),
                Analytics::class.java,
                Crashes::class.java
        )
        notesDb = Room.databaseBuilder(
                applicationContext,
                NotesDb::class.java,
                "notes")
                .build()
        shoppingListDb = Room.databaseBuilder(
                applicationContext,
                ShoppingListDb::class.java,
                "shoppingList")
                .build()
    }

}
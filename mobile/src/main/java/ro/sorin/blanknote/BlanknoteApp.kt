package ro.sorin.blanknote

import android.app.Application
import android.os.Build
import androidx.room.Room
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.phraseapp.android.sdk.PhraseApp
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

        val phraseAppEnvId = if (BuildConfig.DEBUG) {
            "mdXe7mjkOmzQGi3luyHB5NMCLyYAobAaCPhlUPYsk_Q"
        } else {
            "mN8KZveXa6_gY1rb3MlJ51Ecw5JlnAlfVoh_DbLcUmA"
        }
        PhraseApp.setup(this, "a80060a88c676c5c3aa24e30904431c2", phraseAppEnvId)
        PhraseApp.updateTranslations()
    }
}
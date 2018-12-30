package ro.sorin.blanknote

import android.app.Application
import androidx.room.Room
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import ro.sorin.blanknote.db.NotesDb
import ro.sorin.blanknote.db.ShoppingListDb

class BlanknoteApp : Application() {

    lateinit var notesDb: NotesDb
    lateinit var shoppingListDb: ShoppingListDb

    override fun onCreate() {
        super.onCreate()

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
                "outOfFood")
                .build()

    }
}
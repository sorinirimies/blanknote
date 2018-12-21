package ro.sorin.blanknote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import kotlinx.android.synthetic.main.activity_main.*
import ro.sorin.blanknote.ui.notes.NotesFragment
import ro.sorin.blanknote.ui.user.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_notes -> supportFragmentManager.transaction {
                    replace(
                        R.id.fragContainer,
                        NotesFragment.newInstance(),
                        NotesFragment.TAG_NOTES
                    )
                }
                R.id.action_user_settings -> supportFragmentManager.transaction {
                    replace(
                        R.id.fragContainer, UserFragment.newInstance(),
                        UserFragment.TAG_USER
                    )
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}

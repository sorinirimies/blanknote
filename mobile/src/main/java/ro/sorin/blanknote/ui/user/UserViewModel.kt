package ro.sorin.blanknote.ui.user

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ro.sorin.blanknote.utils.CoroutineViewModel


class UserViewModel : CoroutineViewModel() {
    val fireAuth by lazy { FirebaseAuth.getInstance() }
    val userLoggedInOut = MutableLiveData<FirebaseUser?>()

    fun getUser(id: String) = launch {
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {

            }

        }
    }

    fun logoutUser() = fireAuth.signOut().also { userLoggedInOut.postValue(null) }

}

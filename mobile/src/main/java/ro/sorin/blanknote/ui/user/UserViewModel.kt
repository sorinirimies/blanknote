package ro.sorin.blanknote.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ro.sorin.blanknote.model.Note


class UserViewModel : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext = Dispatchers.Main + job

    val notes = MutableLiveData<List<Note>>()


    fun getUser(id: String) = launch {
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {

            }

        }
    }


    override fun onCleared() {
        job.cancel()
    }
}

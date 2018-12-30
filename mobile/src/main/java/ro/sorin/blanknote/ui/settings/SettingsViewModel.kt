package ro.sorin.blanknote.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SettingsViewModel : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
    }
}
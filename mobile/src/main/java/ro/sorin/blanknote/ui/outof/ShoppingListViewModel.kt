package ro.sorin.blanknote.ui.outof

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class WereOutOfViewModel : ViewModel(), CoroutineScope {
    val job by lazy(LazyThreadSafetyMode.NONE) { Job() }

    override val coroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
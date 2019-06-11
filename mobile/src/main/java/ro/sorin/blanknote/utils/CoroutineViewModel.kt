package ro.sorin.blanknote.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class CoroutineViewModel(dispatcher : CoroutineDispatcher = Dispatchers.IO) : ViewModel(), CoroutineScope {

    private val job by lazy(LazyThreadSafetyMode.NONE) { Job() }
    override val coroutineContext = job + dispatcher

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
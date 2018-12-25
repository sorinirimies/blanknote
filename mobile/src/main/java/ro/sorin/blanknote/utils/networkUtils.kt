package ro.sorin.blanknote.utils

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit

val moshi by lazy(LazyThreadSafetyMode.NONE) {
    Moshi.Builder().build()
}
val retrofit: Retrofit by lazy(LazyThreadSafetyMode.NONE) {
    Retrofit.Builder().baseUrl("")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}
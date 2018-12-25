package ro.sorin.blanknote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Note(val id: String, val name: String)
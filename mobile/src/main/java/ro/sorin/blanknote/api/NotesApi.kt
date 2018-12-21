package ro.sorin.blanknote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.model.NoteRequest

interface NotesApi {

    @GET("todo")
    suspend fun getNotes(@Query("userId") userId: String? = null): Deferred<List<Note>>

    @GET("todo/{id}")
    suspend fun getNote(@Path("id") id: String): Deferred<Note>

    @POST("todo")
    suspend fun addNote(@Body note: NoteRequest): Deferred<Note>

    @PUT("todo/{id}")
    suspend fun updateNote(@Body note: NoteRequest, @Path("id") id: String): Deferred<Note>
}
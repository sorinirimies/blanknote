package ro.sorin.blanknote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.model.OutOfFoodRequest

const val notesEndpoint = "notes"

interface NotesApi {

    @GET(notesEndpoint)
    suspend fun getNotes(@Query("userId") userId: String? = null): Deferred<List<Note>>

    @GET("$notesEndpoint/{id}")
    suspend fun getNote(@Path("id") id: String): Deferred<Note>

    @POST(notesEndpoint)
    suspend fun addNote(@Body outOfFood: OutOfFoodRequest): Deferred<Note>

    @PUT("$notesEndpoint/{id}")
    suspend fun updateNote(@Body outOfFood: OutOfFoodRequest, @Path("id") id: String): Deferred<Note>
}
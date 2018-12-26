package ro.sorin.blanknote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.model.OutOfFoodRequest

interface NotesApi {

    @GET("note")
    suspend fun getNotes(@Query("userId") userId: String? = null): Deferred<List<Note>>

    @GET("note/{id}")
    suspend fun getNote(@Path("id") id: String): Deferred<Note>

    @POST("note")
    suspend fun addNote(@Body outOfFood: OutOfFoodRequest): Deferred<Note>

    @PUT("note/{id}")
    suspend fun updateNote(@Body outOfFood: OutOfFoodRequest, @Path("id") id: String): Deferred<Note>
}
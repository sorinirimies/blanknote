package ro.sorin.blanknote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.model.ShoppingListRequest

const val notesEndpoint = "notes"

interface NotesApi {

    @GET(shoppingListEndpoint)
    suspend fun getNotes(@Query("userId") userId: String? = null): Deferred<List<Note>>

    @GET("$shoppingListEndpoint/{id}")
    suspend fun getNote(@Path("id") id: String): Deferred<Note>

    @POST(shoppingListEndpoint)
    suspend fun addNote(@Body shoppingList: ShoppingListRequest): Deferred<Note>

    @PUT("$shoppingListEndpoint/{id}")
    suspend fun updateNote(@Body shoppingList: ShoppingListRequest, @Path("id") id: String): Deferred<Note>
}
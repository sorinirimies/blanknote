package ro.sorin.blanknote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ro.sorin.blanknote.model.Note
import ro.sorin.blanknote.model.ShoppingItem
import ro.sorin.blanknote.model.ShoppingList
import ro.sorin.blanknote.model.ShoppingListRequest

const val shoppingListEndpoint = "shopping"

interface ShoppingListApi {

    @GET(shoppingListEndpoint)
    suspend fun getNotes(@Query("id") id: String? = null): Deferred<List<ShoppingList>>

    @GET("$shoppingListEndpoint/{id}")
    suspend fun getNote(@Path("id") id: String): Deferred<ShoppingList>

    @POST(shoppingListEndpoint)
    suspend fun addNote(@Body shoppingList: ShoppingListRequest): Deferred<ShoppingList>

    @PUT("$shoppingListEndpoint/{id}")
    suspend fun updateNote(@Body shoppingList: ShoppingListRequest, @Path("id") id: String): Deferred<ShoppingList>
}
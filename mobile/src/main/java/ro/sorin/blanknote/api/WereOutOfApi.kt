package ro.sorin.blanknote.api

import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ro.sorin.blanknote.model.ShoppingList
import ro.sorin.blanknote.model.ShoppingListRequest

const val shoppingListEndpoint = "shopping"

interface ShoppingListApi {

    @GET(shoppingListEndpoint)
    suspend fun getOutOfList(@Query("id") id: String? = null): Deferred<List<ShoppingList>>

    @GET("$shoppingListEndpoint/{id}")
    suspend fun getShoppingList(@Path("id") id: String): Deferred<ShoppingList>

    @POST(shoppingListEndpoint)
    suspend fun addShoppingList(@Body shoppingList: ShoppingListRequest): Deferred<ShoppingList>

    @PUT("$shoppingListEndpoint/{id}")
    suspend fun updateShoppingList(@Body shoppingList: ShoppingListRequest, @Path("id") id:
    String): Deferred<ShoppingList>
}
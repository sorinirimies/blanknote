package ro.sorin.blanknote.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ro.sorin.blanknote.model.ShoppingItem
import ro.sorin.blanknote.model.ShoppingList
import ro.sorin.blanknote.model.ShoppingListListWithItems

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingLists(): LiveData<List<ShoppingList>>

    @Transaction
    @Query("SELECT * FROM shoppingitem WHERE shoppingListId = :shoppingListId")
    fun getShoppingItemsFromList(shoppingListId: Long): LiveData<List<ShoppingListListWithItems>>

    @Query("SELECT * FROM shopping_list WHERE id IN (:uids)")
    fun loadAllByIds(uids: IntArray): LiveData<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_list WHERE list_name LIKE :listName  LIMIT 1")
    fun findByName(listName: String): LiveData<ShoppingList>

    @Insert
    fun insertShoppingList(shoppingList: ShoppingList)

    @Insert
    fun insertAll(vararg shoppingList: ShoppingList)

    @Delete
    fun deleteShoppingList(shoppingList: ShoppingList)

    @Delete
    fun deleteShoppingItem(shoppingItem: ShoppingItem)
}
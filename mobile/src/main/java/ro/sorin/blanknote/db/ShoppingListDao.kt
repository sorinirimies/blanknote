package ro.sorin.blanknote.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ro.sorin.blanknote.model.ShoppingItem
import ro.sorin.blanknote.model.ShoppingList

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM ShoppingItem WHERE id = :shoppingListId")
    fun getShoppingItems(shoppingListId: Long): LiveData<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_list WHERE id IN (:ids)")
    fun loadAllShoppingListsByIds(ids: IntArray): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_list WHERE name LIKE :listName  LIMIT 1")
    fun findByName(listName: String): LiveData<ShoppingList>

    @Insert(onConflict = REPLACE)
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Insert(onConflict = REPLACE)
    fun insertShoppingList(shoppingList: ShoppingList)

    @Insert(onConflict = REPLACE)
    fun insertShoppingLists(vararg shoppingList: ShoppingList)

    @Delete
    fun deleteShoppingList(shoppingList: ShoppingList)

    @Delete
    fun deleteShoppingItem(shoppingItem: ShoppingItem)
}
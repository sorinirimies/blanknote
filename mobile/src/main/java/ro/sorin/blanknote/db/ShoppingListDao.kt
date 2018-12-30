package ro.sorin.blanknote.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ro.sorin.blanknote.model.ShoppingItem
import ro.sorin.blanknote.model.ShoppingList

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingLists(): List<ShoppingList>

    @Query("SELECT * FROM shoppingitem WHERE shoppingListId = :shoppingListId")
    fun getAllShoppingItems(shoppingListId: Long): List<ShoppingItem>

    @Query("SELECT * FROM shopping_list WHERE id IN (:uids)")
    fun loadAllByIds(uids: IntArray): List<ShoppingItem>

    @Query("SELECT * FROM shopping_list WHERE list_name LIKE :listName  LIMIT 1")
    fun findByName(listName: String): ShoppingList

    @Insert
    fun insertAll(vararg shoppingList: ShoppingList)

    @Delete
    fun delete(shoppingList: ShoppingList)
}
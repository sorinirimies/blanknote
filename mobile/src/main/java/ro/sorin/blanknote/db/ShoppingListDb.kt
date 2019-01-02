package ro.sorin.blanknote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ro.sorin.blanknote.model.ShoppingItem
import ro.sorin.blanknote.model.ShoppingList

@Database(entities = [ShoppingList::class, ShoppingItem::class],
        version = 1,
        exportSchema = false)
abstract class ShoppingListDb : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}
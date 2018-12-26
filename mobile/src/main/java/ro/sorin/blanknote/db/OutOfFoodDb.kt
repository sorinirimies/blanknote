package ro.sorin.blanknote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ro.sorin.blanknote.model.OutOfFoodItem
import ro.sorin.blanknote.model.OutOfFoodList

@Database(entities = [OutOfFoodList::class, OutOfFoodItem::class], version = 1)
abstract class OutOfFoodDb : RoomDatabase() {
    abstract fun outOfFoodListDao(): OutOfFoodDao
}
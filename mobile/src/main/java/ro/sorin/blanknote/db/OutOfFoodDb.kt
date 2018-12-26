package ro.sorin.blanknote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ro.sorin.blanknote.model.OutOfFood
import ro.sorin.blanknote.model.OutOfFoodList

@Database(entities = [OutOfFoodList::class, OutOfFood::class], version = 1)
abstract class OutOfFoodDb : RoomDatabase() {
    abstract fun outOfFoodListDao(): OutOfFoodDao
}
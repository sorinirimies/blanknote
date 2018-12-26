package ro.sorin.blanknote.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ro.sorin.blanknote.model.OutOfFoodList

@Dao
interface OutOfFoodDao {

    @Query("SELECT * FROM out_of_food_list")
    fun getAll(): List<OutOfFoodList>

    @Query("SELECT * FROM out_of_food_list WHERE uid IN (:uids)")
    fun loadAllByIds(uids: IntArray): List<OutOfFoodList>

    @Query("SELECT * FROM out_of_food_list WHERE list_name LIKE :listName  LIMIT 1")
    fun findByName(listName: String): OutOfFoodList

    @Insert
    fun insertAll(vararg outOfFoodList: OutOfFoodList)

    @Delete
    fun delete(outOfFoodList: OutOfFoodList)
}
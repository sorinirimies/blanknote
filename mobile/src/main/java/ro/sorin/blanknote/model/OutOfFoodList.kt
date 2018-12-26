package ro.sorin.blanknote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "out_of_food_list")
data class OutOfFoodList(@PrimaryKey(autoGenerate = true) var uid: Long = 0,
                         @ColumnInfo(name = "list_name") var listName: String?,
                         @ColumnInfo(name = "list_description") var listDescription: String?,
                         @ColumnInfo(name = "items") var items: List<OutOfFoodItem>
)
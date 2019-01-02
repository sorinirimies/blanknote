package ro.sorin.blanknote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "shopping_list")
data class ShoppingList(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                        @ColumnInfo(name = "name") var listName: String,
                        @ColumnInfo(name = "description") var listDescription: String?)
package ro.sorin.blanknote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class ShoppingItem(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                        @ColumnInfo(name = "shoppingListId") var shoppingListId: Long,
                        @ColumnInfo(name = "name") var name: String,
                        @ColumnInfo(name = "quantity") var quantity: Int = 0)
package ro.sorin.blanknote.model

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingListListWithItems(@Embedded
                                     var shoppingList: ShoppingList,
                                     @Relation(parentColumn = "id",
                                               entityColumn = "shoppingListId",
                                               entity = ShoppingItem::class)
                                     var shoppingItemList: List<ShoppingItem>)
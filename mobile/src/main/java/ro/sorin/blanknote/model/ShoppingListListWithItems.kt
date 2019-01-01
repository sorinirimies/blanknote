package ro.sorin.blanknote.model

import androidx.room.Embedded
import androidx.room.Relation

class ShoppingListListWithItems {

    @Embedded
    lateinit var shoppingList: ShoppingList

    @Relation(parentColumn = "id",
            entityColumn = "shoppingListId",
            entity = ShoppingItem::class)
    var shoppingItems: List<ShoppingItem> = arrayListOf()
}
package ro.sorin.blanknote.utils

import ro.sorin.blanknote.BlanknoteApp
import ro.sorin.blanknote.db.ShoppingListRepository

val shoppingListRepository by lazy {
    ShoppingListRepository(BlanknoteApp.shoppingListDb.outOfFoodListDao())
}
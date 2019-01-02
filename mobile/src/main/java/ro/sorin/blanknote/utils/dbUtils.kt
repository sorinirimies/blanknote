package ro.sorin.blanknote.utils

import ro.sorin.blanknote.BlanknoteApp
import ro.sorin.blanknote.db.NotesRepository
import ro.sorin.blanknote.db.ShoppingListRepository

val shoppingListRepository by lazy {
    ShoppingListRepository(BlanknoteApp.shoppingListDb.shoppingListDao())
}

val notesRepository by lazy {
    NotesRepository(BlanknoteApp.notesDb.notesDao())
}
package com.example.wishlistapp

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRespository(wishDao = database.wishDoa())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java , "wishlist.db").build()
    }
}
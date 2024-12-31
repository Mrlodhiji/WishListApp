package com.example.wishlistapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wishlistapp.Data.Wish
import com.example.wishlistapp.Data.WishDao

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)

abstract class WishDatabase :RoomDatabase() {
  abstract fun wishDoa(): WishDao
}
package com.example.wishlistapp

import com.example.wishlistapp.Data.Wish
import com.example.wishlistapp.Data.WishDao
import kotlinx.coroutines.flow.Flow

class WishRespository(
    private val wishDao: WishDao
){
    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getAllWish():Flow<List<Wish>>{
       return wishDao.getAllWishes()
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

    fun getAWishById(id:Int):Flow<Wish> = wishDao.getWishById(id)
}
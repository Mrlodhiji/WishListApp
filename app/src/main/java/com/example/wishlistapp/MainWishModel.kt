package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.Data.Wish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainWishModel(
    private val wishRep :WishRespository = Graph.wishRepository
):ViewModel() {
    var wishTitle by mutableStateOf("");
    var wishDescription by mutableStateOf("");

    fun onWishTitleChanged(newString :String){
        wishTitle = newString
    }
    fun onWishDescriptionChanged(newString :String){
        wishDescription = newString
    }

    lateinit var getAllWish :Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWish = wishRep.getAllWish()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRep.addWish(wish)
        }
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRep.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRep.deleteWish(wish)
        }
    }
    fun getWishById(id:Int):Flow<Wish>{
        return wishRep.getAWishById(id)
    }
}
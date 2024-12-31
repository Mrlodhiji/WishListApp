package com.example.wishlistapp

sealed class Screen(var route:String){
    object HomePage : Screen("home_screen")
    object AddPage : Screen("add_screen")
}
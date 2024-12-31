package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun navigation(
    viewModel : MainWishModel=viewModel(),
    navController:NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = Screen.HomePage.route) {
          composable(Screen.HomePage.route){
              HomeView(navController, viewModel)
          }
          composable(Screen.AddPage.route+"/{id}",
              arguments = listOf(
                 navArgument("id"){
                     type= NavType.IntType
                     defaultValue =  0
                 }
              )
              ){entry->
              var id = if (entry.arguments != null) entry.arguments!!.getInt("id") else 0
              addEditDetail(id = id, viewModel = viewModel, navController =navController )
          }
    }
}
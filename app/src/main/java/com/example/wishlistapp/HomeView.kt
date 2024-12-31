package com.example.wishlistapp

import MyTopAppBar
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.Data.Wish

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeView(
    navController: NavController,
    viewModel: MainWishModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTopAppBar(title = "wishlist", {
                println("call hua")
                Toast.makeText(context, "clicked Button ", Toast.LENGTH_SHORT).show()
            })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddPage.route + "/0")
                },
                containerColor = Color.Black,
                modifier = Modifier.padding(20.dp),
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {

        var wishList = viewModel.getAllWish.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(wishList.value , key = {wish->wish.id}) { wish ->

                val dismissState = rememberDismissState(
                    confirmValueChange = { dismissValue ->

                        if (dismissValue== DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    background = {
                                 val color by animateColorAsState(
                                     if (dismissState.dismissDirection==DismissDirection.EndToStart) Color.Red else Color.Transparent
                                 )

                        Box(modifier = Modifier
                            .fillMaxSize().background(color)
                            .padding(20.dp),
                          contentAlignment = Alignment.CenterEnd
                        ){
                        Icon(imageVector = Icons.Default.Delete, contentDescription =null )
                        }
                    },
                    dismissContent = {
                        // Your dismissable item's content
                        wishItems(wish = wish) {
                            var id = wish.id
                            navController.navigate(Screen.AddPage.route + "/$id")
                        }
                    }
                )


            }
        }
    }
}

@Composable
fun wishItems(wish: Wish, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = wish.title, style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = wish.description, style = TextStyle(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}


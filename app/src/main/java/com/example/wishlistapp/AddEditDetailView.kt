package com.example.wishlistapp

import MyTopAppBar
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.Data.Wish


@Composable
fun addEditDetail(
    id: Int,
    viewModel: MainWishModel,
    navController: NavController
) {
    var context = LocalContext.current
    if (id != 0) {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0, "", ""))
        viewModel.wishTitle = wish.value.title
        viewModel.wishDescription = wish.value.description
    } else {
        viewModel.wishTitle = ""
        viewModel.wishDescription = ""
    }
    Scaffold(
        topBar = {
            MyTopAppBar(title = if (id != 0) "Update Wish" else "Add wish") {
                navController.navigateUp()
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            wishTextField(title = "Title", value = viewModel.wishTitle) {
                viewModel.onWishTitleChanged(it)
            }

            Spacer(modifier = Modifier.height(10.dp))

            wishTextField(title = "Description", value = viewModel.wishDescription) {
                viewModel.onWishDescriptionChanged(it)
            }

            Button(onClick = {
                if (viewModel.wishTitle.isNotEmpty() &&
                    viewModel.wishDescription.isNotEmpty()
                ) {
                    if (id != 0) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitle.trim(),
                                description = viewModel.wishDescription.trim()
                            )
                        )
                    } else {
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitle.trim(),
                                description = viewModel.wishDescription.trim()
                            )
                        )
                    }
                } else {
                    Toast.makeText(context, "Fill the fields first", Toast.LENGTH_SHORT).show()
                }
                navController.navigateUp()
            }) {
                Text(if (id != 0) "Update Wish" else "Add wish");
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun wishTextField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = title, color = Color.Black) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            unfocusedBorderColor = Color.Black
        )
    )
}
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    onClickBack:  () -> Unit = {}
) {
   var navigationIconButton : (@Composable ()-> Unit)? =
       {
           if (!title.contains("wishlist")){
               IconButton(onClick = { onClickBack() }) {
                   Icon(imageVector = Icons.Filled.ArrowBack,
                       tint = Color.White,
                       contentDescription =null )
               }
           }else null

   }
    if (navigationIconButton != null) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .heightIn(max = 24.dp),
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue
            ),
            navigationIcon = navigationIconButton
        )
    }
}

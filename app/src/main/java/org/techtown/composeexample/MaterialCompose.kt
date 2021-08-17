package org.techtown.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.techtown.composeexample.ui.theme.ComposeExampleTheme

class MaterialCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                LayoutCodeLab()
            }
        }
    }
}

@Composable
fun LayoutCodeLab(){
    Scaffold(
        topBar = { // Scaffold has a slot for a top AppBar with the topBar parameter of
                   // type @Composable () -> Unit, meaning we can fill the slot with any composable we want
            TopAppBar(
                title = { Text(text = "LayoutsCodelab") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                })
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding).padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = "Hi there")
        Text(text = "Thanks for going through the layouts codelab")
    }
}

@Preview
@Composable
fun LayoutCodelabPreview(){
    ComposeExampleTheme {
        LayoutCodeLab()
    }
}
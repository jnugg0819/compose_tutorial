package org.techtown.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import org.techtown.composeexample.ui.theme.ComposeExampleTheme

class ComposeList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                ScrollingList()
            }
        }
    }
}

@Composable
fun SimpleList(){
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState){
        items(100){
            Text(text = "Item #$it")
        }
    }
}

@Composable
fun ImageList(){
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState){
        items(100){
            ImageListItem(it)
        }
    }
}

@Composable
fun ImageListItem(index:Int){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ScrollingList(){
    val listSize = 100
    // 스크롤 포지션 저장.
    val scrollState = rememberLazyListState()
    // Coroutin Scop저장 여기서 탑으로 끝으로가는 동작이 구현될것이다.
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            //0이면 맨앞으로.
            Button(onClick = { coroutineScope.launch { scrollState.animateScrollToItem(0) } }) {
                Text(text = "Scroll to Top")
            }
            //리스트의 맨끝으로.
            Button(onClick = { coroutineScope.launch { scrollState.animateScrollToItem(listSize - 1) } }) {
                Text(text = "Scroll to end")
            }
        }
        
        LazyColumn(state = scrollState){
            items(listSize){
                ImageListItem(index = it)
            }
        }
        
    }
}

@Composable
@Preview
fun SimpleListPreView(){
    ComposeExampleTheme {
        ScrollingList()
    }
}


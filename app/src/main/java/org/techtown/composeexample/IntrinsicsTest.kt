package org.techtown.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.techtown.composeexample.ui.theme.ComposeExampleTheme

class IntrinsicsTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
                    TwoTexts(text1 = "Hi", text2 = "there")
//                }
            }
        }
    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1:String, text2:String){
    Row(modifier = modifier.height(IntrinsicSize.Min)) { //its children being forced to be as tall as their minimum intrinsic height.
        Text(modifier = Modifier
            .weight(1f)
            .padding(start = 4.dp)
            .wrapContentWidth(Alignment.Start),
        text = text1)

        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))

        Text(modifier = Modifier
            .weight(1f)
            .padding(end = 4.dp)
            .wrapContentWidth(Alignment.End),
        text = text2)
    }
}

@Preview
@Composable
fun TwoTextsPreview(){
    ComposeExampleTheme {
        androidx.compose.material.Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}
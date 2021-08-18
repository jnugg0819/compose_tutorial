package org.techtown.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.techtown.composeexample.ui.theme.ComposeExampleTheme

class CustomCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                Modifier.firstBaseLineToTop(32.dp)
            }
        }
    }
}

fun Modifier.firstBaseLineToTop(firstBaselineToTop:Dp) = this.then(
  layout { measurable, constraints ->
      val placeable = measurable.measure(constraints = constraints)

      //Check the composable has a first baseline
      check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
      val firstBaseline = placeable[FirstBaseline]

      //Height of the composable with padding - first baseline
      val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
      val height = placeable.height + placeableY
      layout(placeable.width,height){
          placeable.placeRelative(0,placeableY)
      }
  }
)

@Preview
@Composable
fun TextWithNormalPaddingPreview(){
    ComposeExampleTheme {
        Text(text = "Hi there",Modifier.padding(top = 32.dp))
    }
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview(){
    ComposeExampleTheme {
        Text(text = "Hi there",Modifier.firstBaseLineToTop(32.dp))
    }
}

@Preview
@Composable
fun BodyContent2(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Composable
fun MyOwnColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit){

    Layout(content = content, modifier = modifier){ measurables, constraints ->
        val placeables = measurables.map { measurable ->
            //각 자식들의 치수를 재는거지.
            measurable.measure(constraints = constraints)
        }

        //y값 위에서부터 0이니까 디폴트 0으로 두고요.
        var yPosition =0

        layout(constraints.maxWidth, constraints.maxHeight){
            //위자식들 돌리면서 포지션 잡아준다.
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)

                //자식들의 높이만큼 y포지션 더해줌.
                yPosition += placeable.height
                yPosition += 40
            }
        }
    }

}

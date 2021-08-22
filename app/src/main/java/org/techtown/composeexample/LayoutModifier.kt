package org.techtown.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import org.techtown.composeexample.ui.theme.ComposeExampleTheme

class LayoutModifier : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                ModifierBodyContent()
            }
        }
    }
}

@Stable
private fun Modifier.padding(all: Dp){
    this.then(
        PaddingModifier(start = all, top = all, end = all, bottom = all, rtlAware = true)
    )
}

private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean
) : LayoutModifier{
    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        val horizontal = start.roundToPx() + end.roundToPx()
        val vertical = top.roundToPx() + bottom.roundToPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, - vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)

        return layout(width = width, height = height){
            if(rtlAware)
                placeable.placeRelative(start.roundToPx(), top.roundToPx())
            else
                placeable.place(start.roundToPx(), top.roundToPx())
        }
    }

}

@Composable
private fun ModifierBodyContent(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .background(color = Color.LightGray, shape = RectangleShape)
            .size(200.dp)
            .padding(16.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        StaggeeredGrid {
            for(topic in topics)
                Chip(modifier = Modifier.padding(8.dp), text = topic)
        }
    }
}

@Preview
@Composable
private fun ModifierPreview(){
    ComposeExampleTheme {
        ModifierBodyContent()
    }
}
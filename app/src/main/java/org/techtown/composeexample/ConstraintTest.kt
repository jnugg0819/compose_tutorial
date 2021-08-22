package org.techtown.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import org.techtown.composeexample.ui.theme.ComposeExampleTheme

class ConstraintText : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                ConstraintLayoutContent()
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent(){
    ConstraintLayout {


        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1){
                top.linkTo(parent.top, margin = 16.dp)
            }

            ) {
            Text(text = "Button1")
        }

        Text(text = "Text", Modifier.constrainAs(text){
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })
        
        val barrier = createEndBarrier(button1, text)
        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button2){
          top.linkTo(parent.top, margin = 16.dp)
          start.linkTo(barrier)  
        }){
            Text(text = "Button2")
        }
    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview(){
    ComposeExampleTheme {
        ConstraintLayoutContent()
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent.atLeast(100.dp)
            }
        )
    }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview(){
    ComposeExampleTheme {
        LargeConstraintLayout()
    }
}

@Preview
@Composable
fun DecoupleConstraintLayoutPreview(){
    ComposeExampleTheme {
        DecoupledConstraintLayout()
    }
}

@Composable
fun DecoupledConstraintLayout(){
    BoxWithConstraints {
        val contraints = if(maxWidth < maxHeight){
            decoupedConstraints(margin = 16.dp)
        } else {
            decoupedConstraints(margin = 32.dp)
        }

        ConstraintLayout(contraints) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button")) {
                Text(text = "Button")
            }

            Text(text = "Text", Modifier.layoutId("text"))
        }

    }
}

private fun decoupedConstraints(margin:Dp) :ConstraintSet{
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button){
            top.linkTo(parent.top, margin = margin)
        }

        constrain(text){
            top.linkTo(button.bottom, margin = margin)
        }
    }
}
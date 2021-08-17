package com.hdesrosiers.composebasicsii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hdesrosiers.composebasicsii.ui.theme.ComposeBasicsIITheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApp {
        MyScreenContent()
      }
    }
  }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
  ComposeBasicsIITheme {
    Surface(color = Color.Yellow) {
      content()
    }
  }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
  val counterState = remember { mutableStateOf(0) }

  Column(
    modifier = Modifier
      .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    NameList(
      names = names,
      Modifier.weight(1f)
    )
    Counter(
      count = counterState.value,
      updateCount = { newCount ->
        counterState.value = newCount
      }
    )
  }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
  LazyColumn(modifier = modifier) {
    items(items = names) { name ->
      Greeting(name = name)
      Divider(color = Color.Black)
    }
  }
}

@Composable
fun Counter(
  count: Int,
  modifier: Modifier = Modifier,
  updateCount: (Int) -> Unit
) {
  Button(
    onClick = { updateCount(count + 1) },
    colors = ButtonDefaults.buttonColors(
      backgroundColor = if (count < 5) Color.Green else Color.White
    )
  ) {
    Text(text = "I've been clicked $count times")
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyApp(
    content = { MyScreenContent() }
  )
}
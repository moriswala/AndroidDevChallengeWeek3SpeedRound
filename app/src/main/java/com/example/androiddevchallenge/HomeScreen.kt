package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.ui.common.HorizontalListItem
import com.example.androiddevchallenge.ui.list.InstagramStories
import com.example.androiddevchallenge.ui.theme.AppThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun HomeOnboard(value: AppThemeState, navController: NavHostController) {
    var loggedIn by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Crossfade(targetState = loggedIn) {

        HomeScreen(value) {
            coroutineScope.launch {
                delay(2000)
                loggedIn = true
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(value: AppThemeState, onLoginSuccess: () -> Unit) {
    Scaffold {
        Box(modifier = Modifier
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background), content = {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                item {
                    searchBar()
                    itemTitle(Modifier.height(40.dp), LocalContext.current.getString(R.string.favorite_collection).toUpperCase())
                    horizontalListView()
                    horizontalListView()
                    itemTitle(Modifier.height(40.dp),
                        LocalContext.current.getString(R.string.align_body).toUpperCase(),
                    )
                    InstagramStories()
                    itemTitle(Modifier.height(40.dp),
                        LocalContext.current.getString(R.string.align_mind).toUpperCase()
                    )
                    InstagramStories()
                }
            }
        })
    }
}

@Composable
fun itemTitle(modifier: Modifier, title: String) {

    Row(modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.Bottom,
    horizontalArrangement = Arrangement.Start){
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )
    }
}


@Composable
fun horizontalListView() {
    val list = remember { DemoDataProvider.itemList }
        LazyRow(
            modifier = Modifier.padding(end = 16.dp)
        ) {
            items(
                items = list,
                itemContent = {
                    HorizontalListItem(
                        it,
                        Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                })
        }
}


@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@ExperimentalComposeUiApi
@Composable
fun searchBar() {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(top = 56.dp)
        ,
        value = query,
        onValueChange = { query = it },
        label = {
            Text(
                text = LocalContext.current.getString(R.string.search),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onExecuteSearch()
                keyboardController?.hideSoftwareKeyboard()
            },
        ),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colors.primary),
    )
}

fun onExecuteSearch() {
    TODO("Not yet implemented")
}


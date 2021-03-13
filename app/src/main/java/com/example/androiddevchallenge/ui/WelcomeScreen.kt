package com.example.androiddevchallenge.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

import com.example.androiddevchallenge.ui.theme.AppThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun WelcomeOnboard(value: AppThemeState) {
    var loggedIn by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Crossfade(targetState = loggedIn) {

        WelcomeScreen(value) {
            coroutineScope.launch {
                delay(2000)
                loggedIn = true
            }
        }
    }
}

@Composable
fun WelcomeScreen(value: AppThemeState, onLoginSuccess: () -> Unit) {
    Scaffold {
        Box(modifier = Modifier.fillMaxHeight() ,content = {
            Image(imageVector = ImageVector.vectorResource(id = if(value.darkTheme) R.drawable.dark_welcome else R.drawable.light_welcome), contentDescription = "background",modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
            LazyColumn(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                item {
                    Text(text = LocalContext.current.getString(R.string.app_name).toUpperCase(), style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
                item { Spacer(modifier = Modifier.height(32.dp)) }

                item {
                    OutlinedButton(onClick = { }, border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(20), //50% percent
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onPrimary,
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier.fillMaxWidth()
                        .height(72.dp)) {
                        Text(
                            text = LocalContext.current.getString(R.string.signup).toUpperCase(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    OutlinedButton(onClick = {

                    }, border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(20), //50% percent
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.onPrimary,
                            backgroundColor = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .height(72.dp)) {
                        Text(
                            text = LocalContext.current.getString(R.string.login).toUpperCase(),
                            style = MaterialTheme.typography.button,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        })

    }
}

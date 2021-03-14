package com.example.androiddevchallenge.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.NavRoutes
import com.example.androiddevchallenge.R

import com.example.androiddevchallenge.ui.theme.AppThemeState


@Composable
fun WelcomeOnboard(value: AppThemeState, navController: NavHostController) {

    WelcomeScreen(value, navController)

}

@Composable
fun WelcomeScreen(value: AppThemeState, navController: NavHostController) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = MaterialTheme.colors.surface),
            content = {
                Image(
                    imageVector = ImageVector.vectorResource(id = if (value.darkTheme) R.drawable.dark_welcome else R.drawable.light_welcome),
                    contentDescription = "background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    item {
                        Text(
                            text = LocalContext.current.getString(R.string.app_name).toUpperCase(),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                    item { Spacer(modifier = Modifier.height(32.dp)) }

                    item {
                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                            shape = RoundedCornerShape(20), //50% percent
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colors.onPrimary,
                                backgroundColor = MaterialTheme.colors.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(72.dp)
                        ) {
                            Text(
                                text = LocalContext.current.getString(R.string.signup)
                                    .toUpperCase(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.button,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        OutlinedButton(
                            onClick = {
                                navController.navigate(NavRoutes.LOGIN)
                            }, border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                            shape = RoundedCornerShape(20), //50% percent
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colors.onPrimary,
                                backgroundColor = MaterialTheme.colors.secondary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(72.dp)
                        ) {
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

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.ui.HomeScreen
import com.example.androiddevchallenge.ui.ProfileScreen
import com.example.androiddevchallenge.ui.theme.AppThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun HomeOnboard(appThemeState: MutableState<AppThemeState>, navController: NavHostController) {

    Scaffold(
        floatingActionButton = {
            PlayFloatingButton(appThemeState)
        },
    ) {

        // Default home screen state is always HOME
        val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }
        val bottomNavBarContentDescription = stringResource(id = R.string.navigation_bar)

        Column {
            HomeScreenContent(
                homeScreen = homeScreenState.value,
                appThemeState = appThemeState,
                modifier = Modifier.weight(1f)
            )
            BottomNavigationContent(
                modifier = Modifier
                    .semantics { contentDescription = bottomNavBarContentDescription }
                    .testTag("bottom_navigation_bar"),
                homeScreenState = homeScreenState
            )
        }
    }
}

@Composable
fun PlayFloatingButton(appThemeState: MutableState<AppThemeState>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp)
    ) {
        FloatingActionButton(
            onClick = {
            }
        ) {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = stringResource(id = R.string.play),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.onSecondary,
        contentColor = MaterialTheme.colors.secondary
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Spa, contentDescription = null) },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.home).toUpperCase()) },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null) },
            selected = homeScreenState.value == BottomNavType.PROFILE,
            onClick = {
                homeScreenState.value = BottomNavType.PROFILE
                animate = false
            },
            label = { Text(text = stringResource(id = R.string.profile)) }
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    BottomNavType.HOME -> {
                        var loggedIn by remember { mutableStateOf(false) }
                        val coroutineScope = rememberCoroutineScope()
                        HomeScreen(appThemeState.value) {
                            coroutineScope.launch {
                                delay(2000)
                                loggedIn = true
                            }
                        }
                    }
                    BottomNavType.PROFILE -> ProfileScreen(appThemeState.value)
                }
            }
        }
    }
}

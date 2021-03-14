package com.example.androiddevchallenge.ui

import android.animation.ValueAnimator
import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.airbnb.lottie.LottieAnimationView
import com.example.androiddevchallenge.NavRoutes
import com.example.androiddevchallenge.R

import com.example.androiddevchallenge.ui.common.HorizontalDottedProgressBar
import com.example.androiddevchallenge.ui.theme.AppThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun LoginOnboarding(value: AppThemeState, navController: NavHostController) {
    var loggedIn by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Crossfade(targetState = loggedIn) {
        /*if (loggedIn) {
            OnBoardingScreen1 {
                loggedIn = false
            }
        } else {*/
        LoginScreen(value) {
            coroutineScope.launch {
                delay(2000)
                loggedIn = true
                navController.navigate(NavRoutes.HOME)
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(value: AppThemeState, onLoginSuccess: () -> Unit) {
    Scaffold {

        //TextFields
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var hasError by remember { mutableStateOf(false) }
        var passwordVisualTransformation by remember {
            mutableStateOf<VisualTransformation>(
                PasswordVisualTransformation()
            )
        }
        val passwordInteractionState = remember { MutableInteractionSource() }
        val emailInteractionState = remember { MutableInteractionSource() }
        val keyboardController = LocalSoftwareKeyboardController.current

        Box(modifier = Modifier.fillMaxHeight().background(color = MaterialTheme.colors.surface), content = {
            Image(
                imageVector = ImageVector.vectorResource(id = if (value.darkTheme) R.drawable.dark_login else R.drawable.light_login),
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
                        text = LocalContext.current.getString(R.string.login).toUpperCase(),
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                item { Spacer(modifier = Modifier.height(32.dp)) }

                item {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(
                                text = LocalContext.current.getString(R.string.email_address),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.primary
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),

                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hideSoftwareKeyboard()
                            },
                        ),

                        textStyle = MaterialTheme.typography.body1,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.primary,
                            backgroundColor = MaterialTheme.colors.onSurface
                        ),
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
                    TextField(
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    Icons.Filled.Visibility,
                                    contentDescription = stringResource(id = R.string.search),
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                text = LocalContext.current.getString(R.string.password),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.primary
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                        ),

                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hideSoftwareKeyboard()
                            },
                        ),

                        textStyle = MaterialTheme.typography.body1,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.primary,
                            backgroundColor = MaterialTheme.colors.onSurface
                        ),
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    var loading by remember { mutableStateOf(false) }
                    OutlinedButton(
                        onClick = {
                            if (invalidInput(email.text, password.text)) {
                                hasError = true
                                loading = false
                            } else {
                                loading = true
                                hasError = false
                                onLoginSuccess.invoke()
                            }
                        },
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

                        if (loading) {
                            HorizontalDottedProgressBar()
                        } else {
                            Text(
                                text = LocalContext.current.getString(R.string.signup)
                                    .toUpperCase(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.button,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }


                    }
                }

                item {
                    val primaryColor = MaterialTheme.colors.primary
                    val annotatedString = remember {
                        AnnotatedString.Builder("Don't have an account? Sign up")
                            .apply {
                                addStyle(
                                    style = SpanStyle(textDecoration = TextDecoration.Underline),
                                    23,
                                    30
                                )
                            }
                    }
                    Text(
                        text = annotatedString.toAnnotatedString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .clickable(onClick = {}),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                    )
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        })
    }
}

fun invalidInput(email: String, password: String) =
    email.isNullOrBlank() || password.isNullOrBlank()


@Composable
fun LottieLoadingView(context: Context) {
    val lottieView = remember {
        LottieAnimationView(context).apply {
            setAnimation("working.json")
            repeatCount = ValueAnimator.INFINITE
        }
    }
    AndroidView(
        { lottieView }, modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        it.playAnimation()
    }
}
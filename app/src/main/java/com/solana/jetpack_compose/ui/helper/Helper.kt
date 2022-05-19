package com.solana.jetpack_compose.ui.helper

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ShowSnackBar() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
    ) {
        Button(
            onClick = {
                coroutineScope.launch { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = "This is your message",
                        actionLabel = "Do something."
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> Log.d(
                            "SnackbarDemo",
                            "Snackbar's button clicked"
                        )
                    }
                }
            }
        ) {
            Text(text = "A button that shows a Snackbar")
        }
    }
}
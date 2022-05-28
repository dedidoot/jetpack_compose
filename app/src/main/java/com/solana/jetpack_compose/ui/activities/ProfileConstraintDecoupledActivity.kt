package com.solana.jetpack_compose.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solana.jetpack_compose.ui.components.ProfileConstraintDecoupled
import com.solana.jetpack_compose.ui.model.ProfileModel
import com.solana.jetpack_compose.ui.theme.Jetpack_composeTheme
import kotlinx.coroutines.launch

class ProfileConstraintDecoupledActivity : ComponentActivity() {

    private val profileRecommendation = ArrayList<ProfileModel>()
    private val profileStar = ArrayList<ProfileModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDummyData()

        setContent {
            Jetpack_composeTheme {
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize().fillMaxHeight(),
                    scaffoldState = scaffoldState,
                    backgroundColor = Color.White,
                    bottomBar = {
                        Button(onClick = {
                            startActivity(Intent(this, InputActivity::class.java))
                        },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                            shape = RoundedCornerShape(0)) {
                            Text(text = "Open Activity",
                                color = Color.White,
                                fontSize = 18.sp)
                        }
                    }
                ) {
                    LazyColumn {
                        item {
                            Text(
                                text = "User Recommended",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                            )
                        }

                        items(profileRecommendation.size) { index ->
                            Spacer(modifier = Modifier.height(32.dp))
                            val profile = profileRecommendation.getOrNull(index)
                            ProfileConstraintDecoupled(profile?.fullName.orEmpty(), profile?.city.orEmpty(), {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Message User ${profile?.fullName}",
                                    )
                                }
                            }, {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Follow User ${profile?.fullName}",
                                    )
                                }
                            })
                        }

                        item {
                            Text(
                                text = "Profile Star",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                            )
                        }

                        items(profileStar.size) { index ->
                            Spacer(modifier = Modifier.height(32.dp))
                            val profile = profileStar.getOrNull(index)
                            ProfileConstraintDecoupled(profile?.fullName.orEmpty(), profile?.city.orEmpty(), {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Message User ${profile?.fullName}",
                                    )
                                }
                            }, {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Follow User ${profile?.fullName}",
                                    )
                                }
                            })
                        }
                    }
                }
            }
        }
    }

    private fun setupDummyData() {
        for (index in 1 until 100) {
            profileRecommendation.add(ProfileModel("John Doe $index", "City $index"))
        }
        for (index in 1 until 100) {
            profileStar.add(ProfileModel("Lorry $index", "City $index"))
        }
    }
}
package com.solana.jetpack_compose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solana.jetpack_compose.ui.components.ProfileColumn
import com.solana.jetpack_compose.ui.model.ProfileModel
import com.solana.jetpack_compose.ui.theme.Jetpack_composeTheme
import kotlinx.coroutines.launch

class ProfileColumnActivity : ComponentActivity() {

    private val profileRecommendation = ArrayList<ProfileModel>()
    private val profileStar = ArrayList<ProfileModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDummyData()

        setContent {
            Jetpack_composeTheme {
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()
                val profileRecommendationList = rememberSaveable { mutableStateOf(ArrayList<ProfileModel>()) }
                val profileStarList = rememberSaveable { mutableStateOf(ArrayList<ProfileModel>()) }
                profileRecommendationList.value = profileRecommendation
                profileStarList.value = profileStar

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    backgroundColor = Color.White
                ) {
                    LazyColumn {
                        item {
                            Text(
                                text = "User Recommended",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                            )
                        }

                        items(profileRecommendationList.value.size) { index ->
                            Spacer(modifier = Modifier.height(32.dp))
                            val profile = profileRecommendationList.value.getOrNull(index)
                            ProfileColumn(profile?.fullName.orEmpty(), profile?.city.orEmpty(), {
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

                        items(profileStarList.value.size) { index ->
                            Spacer(modifier = Modifier.height(32.dp))
                            val profile = profileStarList.value.getOrNull(index)
                            ProfileColumn(profile?.fullName.orEmpty(), profile?.city.orEmpty(), {
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
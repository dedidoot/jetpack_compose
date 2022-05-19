package com.solana.jetpack_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solana.jetpack_compose.R
import com.solana.jetpack_compose.ui.theme.Jetpack_composeTheme

@Composable
fun ProfileColumn(
    fullName: String, city: String,
    messageOnClickListener: () -> Unit = {},
    followOnClickListener: () -> Unit = {}
) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "profile_image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp)
            )
            Text(text = fullName, fontSize = 25.sp, modifier = Modifier.padding(top = 8.dp))
            Text(text = city, fontSize = 20.sp, modifier = Modifier.padding(top = 4.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    messageOnClickListener()
                }) {
                    Text(text = "Message user")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    followOnClickListener()
                }) {
                    Text(text = "Follow user")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Jetpack_composeTheme {
        //ProfileColumn()
    }
}
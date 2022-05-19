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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.solana.jetpack_compose.R
import com.solana.jetpack_compose.ui.theme.Jetpack_composeTheme

@Composable
fun ProfileConstraint(
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
        ConstraintLayout {
            val (imageId, fullNameId, cityId, buttonLeftId, buttonRightId) = createRefs()
            val guideLineTop = createGuidelineFromTop(0.1f)

            Image(painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "profile_image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp)
                    .constrainAs(imageId) {
                        top.linkTo(guideLineTop)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(text = fullName,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(fullNameId) {
                        top.linkTo(imageId.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(text = city,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .constrainAs(cityId) {
                        top.linkTo(fullNameId.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Button(onClick = {
                messageOnClickListener()
            }, modifier = Modifier.constrainAs(buttonLeftId) {
                top.linkTo(cityId.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(buttonRightId.start)
                width = Dimension.wrapContent
                bottom.linkTo(parent.bottom, margin = 16.dp)
            }) {
                Text(text = "Message user")
            }

            Button(onClick = {
                followOnClickListener()
            }, modifier = Modifier.constrainAs(buttonRightId) {
                top.linkTo(cityId.bottom, margin = 16.dp)
                start.linkTo(buttonLeftId.end)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                bottom.linkTo(parent.bottom, margin = 16.dp)
            }) {
                Text(text = "Follow user")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewConstraint() {
    Jetpack_composeTheme {

    }
}
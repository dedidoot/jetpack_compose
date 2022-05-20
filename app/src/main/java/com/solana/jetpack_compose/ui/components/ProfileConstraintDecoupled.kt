package com.solana.jetpack_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.solana.jetpack_compose.R
import com.solana.jetpack_compose.ui.helper.LayoutId
import com.solana.jetpack_compose.ui.theme.Jetpack_composeTheme

@Composable
fun ProfileConstraintDecoupled(
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

        BoxWithConstraints(propagateMinConstraints = true) {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraints(16.dp)
            } else {
                landscapeConstraints(16.dp)
            }

            ConstraintLayout(constraints) {

                Image(painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "profile_image",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(128.dp)
                        .layoutId(LayoutId.imageLayoutId)
                )
                Text(text = fullName,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .layoutId(LayoutId.fullNameLayoutId)
                )
                Text(text = city,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .layoutId(LayoutId.cityLayoutId)
                )
                Button(onClick = {
                    messageOnClickListener()
                }, modifier = Modifier.layoutId(LayoutId.messageBtnLayoutId)) {
                    Text(text = "Message user")
                }

                Button(onClick = {
                    followOnClickListener()
                }, modifier = Modifier.layoutId(LayoutId.followBtnLayoutId)) {
                    Text(text = "Follow user")
                }
            }
        }
    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor(LayoutId.imageLayoutId)
        val fullName = createRefFor(LayoutId.fullNameLayoutId)
        val city = createRefFor(LayoutId.cityLayoutId)
        val follow = createRefFor(LayoutId.followBtnLayoutId)
        val message = createRefFor(LayoutId.messageBtnLayoutId)

        constrain(image) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(fullName) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(city) {
            top.linkTo(fullName.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(follow) {
            top.linkTo(city.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(message.start)
            bottom.linkTo(parent.bottom, margin = 16.dp)
        }

        constrain(message) {
            top.linkTo(city.bottom, margin = 16.dp)
            start.linkTo(follow.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, margin = 16.dp)
        }
    }
}

private fun landscapeConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor(LayoutId.imageLayoutId)
        val fullName = createRefFor(LayoutId.fullNameLayoutId)
        val city = createRefFor(LayoutId.cityLayoutId)
        val follow = createRefFor(LayoutId.followBtnLayoutId)
        val message = createRefFor(LayoutId.messageBtnLayoutId)

        constrain(image) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            bottom.linkTo(parent.bottom, margin = 16.dp)
        }

        constrain(fullName) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(image.end, margin = 16.dp)
        }

        constrain(city) {
            top.linkTo(fullName.bottom)
            start.linkTo(image.end, margin = 16.dp)
        }

        constrain(follow) {
            top.linkTo(city.bottom, margin = 16.dp)
            start.linkTo(image.end, margin = 16.dp)
            end.linkTo(message.start)
        }

        constrain(message) {
            top.linkTo(city.bottom, margin = 16.dp)
            start.linkTo(follow.end, margin = 16.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewConstraintDecoupled() {
    Jetpack_composeTheme {

    }
}
/*
 * Copyright 2022 Compose Academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package academy.compose.blog.ui

import academy.compose.blog.R
import academy.compose.blog.model.Post
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.*
import coil.compose.AsyncImage
import kotlin.math.absoluteValue

private const val KEY_HEADER = "header"
private const val KEY_TITLE = "title"
private const val KEY_TITLE_BACKGROUND = "title_background"
private const val KEY_UP = "up"
private const val KEY_AVATAR = "avatar"
private const val KEY_AUTHOR = "author"
private const val KEY_EXCERPT = "excerpt"
private const val KEY_TINT = "tint"

@ExperimentalComposeUiApi
@ExperimentalMotionApi
@Composable
fun PostDetail(
    modifier: Modifier = Modifier,
    post: Post,
    handleNavigateUp: () -> Unit
) {
    val scrollState = rememberScrollState()
    MotionLayout(
        modifier = modifier,
        motionScene = motionScene(),
        progress = if (scrollState.maxValue > 0 &&
            LocalConfiguration.current.orientation != ORIENTATION_LANDSCAPE
        ) {
            scrollState.value / scrollState.maxValue.toFloat()
        } else 1f
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(KEY_HEADER),
            model = post.image,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .layoutId(KEY_TITLE_BACKGROUND)
                .background(Color.Black.copy(alpha = 0.4f))
                .fillMaxWidth()
        )

        Text(
            modifier = Modifier
                .layoutId(KEY_TITLE)
                .padding(vertical = 16.dp),
            text = post.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        IconButton(
            modifier = Modifier
                .layoutId(KEY_UP),
            onClick = handleNavigateUp
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_action_up),
                tint = Color.White
            )
        }
        Icon(
            modifier = Modifier
                .size(40.dp)
                .layoutId(KEY_AVATAR),
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = motionProperties(id = KEY_AVATAR).value.color(KEY_TINT)
        )

        Text(
            modifier = Modifier
                .layoutId(KEY_AUTHOR)
                .width(200.dp),
            text = post.author,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .layoutId(KEY_EXCERPT)
                .verticalScroll(scrollState)
                .padding(start = 16.dp, end = 16.dp, bottom = 78.dp),
            text = post.excerpt
        )
    }
}

@ExperimentalMotionApi
@Composable
fun motionScene() = MotionScene {
    val sceneStart = constraintSet {
        val header = createRefFor(KEY_HEADER)
        val title = createRefFor(KEY_TITLE)
        val up = createRefFor(KEY_UP)
        val titleBackground = createRefFor(KEY_TITLE_BACKGROUND)
        val avatar = createRefFor(KEY_AVATAR)
        val author = createRefFor(KEY_AUTHOR)
        val excerpt = createRefFor(KEY_EXCERPT)

        constrain(header) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            height = Dimension.value(280.dp)
        }
        constrain(title) {
            start.linkTo(parent.start)
            bottom.linkTo(header.bottom)
            end.linkTo(parent.end)
        }
        constrain(titleBackground) {
            start.linkTo(parent.start)
            bottom.linkTo(header.bottom)
            end.linkTo(parent.end)
            height = Dimension.value(60.dp)
        }
        constrain(up) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(header.bottom)
            visibility = Visibility.Gone
        }
        constrain(avatar) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(header.bottom, 16.dp)
            customColor(KEY_TINT, Color.Black)
        }
        constrain(author) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(avatar.bottom)
            visibility = Visibility.Visible
        }
        constrain(excerpt) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(author.bottom, 16.dp)
        }
    }
    val sceneEnd = constraintSet {
        val header = createRefFor(KEY_HEADER)
        val title = createRefFor(KEY_TITLE)
        val up = createRefFor(KEY_UP)
        val titleBackground = createRefFor(KEY_TITLE_BACKGROUND)
        val avatar = createRefFor(KEY_AVATAR)
        val author = createRefFor(KEY_AUTHOR)
        val excerpt = createRefFor(KEY_EXCERPT)

        constrain(header) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            height = Dimension.value(60.dp)
        }
        constrain(title) {
            start.linkTo(up.end)
            bottom.linkTo(header.bottom)
        }
        constrain(titleBackground) {
            start.linkTo(parent.start)
            bottom.linkTo(header.bottom)
            end.linkTo(parent.end)
            height = Dimension.value(60.dp)
        }
        constrain(up) {
            start.linkTo(parent.start)
            top.linkTo(header.top)
            bottom.linkTo(header.bottom)
            visibility = Visibility.Visible
        }
        constrain(avatar) {
            top.linkTo(titleBackground.top)
            bottom.linkTo(titleBackground.bottom)
            end.linkTo(parent.end, 8.dp)
            customColor(KEY_TINT, Color.White)
        }
        constrain(author) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(avatar.bottom)
            visibility = Visibility.Gone
        }
        constrain(excerpt) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(titleBackground.bottom, 4.dp)
        }
    }
    defaultTransition(
        from = sceneStart,
        to = sceneEnd
    ) {}
}

@ExperimentalComposeUiApi
@ExperimentalMotionApi
@Preview(showBackground = true)
@Composable
fun Preview_AnimatedPostDetail() {
    MaterialTheme {
        PostDetail(
            post = Post(
                "1",
                "Blog post",
                "hitherejoe",
                "",
                "This is an exceprt",
                "27/03/2022",
            ),
            handleNavigateUp = {}
        )
    }
}



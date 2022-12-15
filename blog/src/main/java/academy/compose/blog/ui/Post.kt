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

import academy.compose.blog.model.Post
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage

@Composable
fun Post(
    modifier: Modifier = Modifier,
    post: Post?
) {
    post?.let {
        Card(
            modifier = modifier.heightIn(265.dp)
        ) {
            ConstraintLayout(
                Modifier
                    .fillMaxWidth()
            ) {
                val (header, excerpt, author, title, date) = createRefs()

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .constrainAs(header) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    model = post.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .constrainAs(title) {
                            bottom.linkTo(header.bottom)
                        }
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.4f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    text = post.title,
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Text(
                    modifier = Modifier
                        .constrainAs(excerpt) {
                            top.linkTo(header.bottom, margin = 12.dp)
                            start.linkTo(title.start)
                            end.linkTo(title.end)
                        }
                        .padding(horizontal = 12.dp),
                    maxLines = 3,
                    overflow = Ellipsis,
                    text = post.excerpt
                )

                Text(
                    modifier = Modifier
                        .padding(end = 12.dp, bottom = 12.dp)
                        .constrainAs(date) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    text = post.publishDate,
                    fontSize = 14.sp
                )

                Text(
                    modifier = Modifier
                        .padding(12.dp)
                        .constrainAs(author) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(date.start)
                        },
                    text = post.author,
                    fontSize = 14.sp
                )
                createHorizontalChain(author, date, chainStyle = ChainStyle.SpreadInside)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Post() {
    MaterialTheme {
        Post(
            post = Post(
                "1",
                "Blog post",
                "hitherejoe",
                "http://picsum.photos/800/400",
                "This is an exceprt",
                "27/03/2022"
            )
        )
    }
}

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
package academy.compose.graphs.ui

import academy.compose.graphs.model.GraphData
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun configureAnimation(
    chartData: List<GraphData>
): Animatable<Float, AnimationVector1D> {
    val transitionAnimation = remember(chartData) {
        Animatable(
            initialValue = 0f
        )
    }
    LaunchedEffect(chartData) {
        transitionAnimation.animateTo(
            1f,
            animationSpec = TweenSpec(durationMillis = 750)
        )
    }
    return transitionAnimation
}
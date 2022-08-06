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
package academy.compose.music

import academy.compose.music.model.NowPlaying
import academy.compose.music.model.NowPlayingState
import academy.compose.music.model.Track
import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.random.Random.Default.nextLong

object TestDataFactory {

    fun randomString() = UUID.randomUUID().toString().substring(0, 10)

    fun makeNowPlaying(
        track: Track = makeTrack(),
        state: NowPlayingState = NowPlayingState.PAUSED,
        progress: Long = track.length / 3
    ): NowPlaying {
        return NowPlaying(
            track,
            progress,
            state
        )
    }

    fun makeTrack(
        isFeatured: Boolean = Math.random() < 0.5,
        isNew: Boolean = Math.random() < 0.5
    ): Track {
        return Track(
            randomString(),
            randomString(),
            randomString(),
            Color.Red,
            length = nextLong(500),
            isFeatured = isFeatured,
            isNew = isNew
        )
    }

    fun makeContentList(count: Int): List<Track> {
        return (0..count).map { makeTrack() }
    }

    fun makeMixedContentList(): List<Track> {
        return listOf(
            makeTrack(false, false),
            makeTrack(true, true),
            makeTrack(true, false),
            makeTrack(false, true),
            makeTrack(true, true),
            makeTrack(false, false),
            makeTrack(false, true),
            makeTrack(true, false)
        )
    }
}
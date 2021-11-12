/*
 * Copyright 2021 Compose Academy
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

package academy.compose.messaging

import academy.compose.messaging.model.Message
import academy.compose.messaging.model.MessageDirection
import android.graphics.Bitmap
import java.util.*

object MessageFactory {

    fun makeMessage(
        text: String? = null,
        image: Int? = null,
        direction: MessageDirection = MessageDirection.SENT
    ): Message {
        return Message(
            "0",
            direction,
            Calendar.getInstance().also {
                it.add(Calendar.DAY_OF_YEAR, -5)
            },
            "Joe Birch",
            message = text,
            image = image
        )
    }

    fun makeMessages(): List<Message> {
        return listOf(
            Message(
                "0",
                MessageDirection.SENT,
                Calendar.getInstance().also {
                    it.add(Calendar.DAY_OF_YEAR, -5)
                    it.set(Calendar.HOUR_OF_DAY, 5)
                },
                "Joe Birch",
                "Hey!"
            ),
            Message(
                "1",
                MessageDirection.RECEIVED,
                Calendar.getInstance().also {
                    it.add(Calendar.DAY_OF_YEAR, -5)
                    it.set(Calendar.HOUR_OF_DAY, 5)
                },
                "Joe Birch",
                "Hey!"
            ),
            Message(
                "2",
                MessageDirection.RECEIVED,
                Calendar.getInstance().also {
                    it.add(Calendar.DAY_OF_YEAR, -4)
                    it.set(Calendar.HOUR_OF_DAY, 4)
                },
                "Joe Birch",
                "How is Roxy? 😊"
            ),
            Message(
                "4",
                MessageDirection.SENT,
                Calendar.getInstance().also {
                    it.add(Calendar.DAY_OF_YEAR, -2)
                },
                "Joe Birch",
                "She is doing great!"
            ),
            Message(
                "5",
                MessageDirection.SENT,
                Calendar.getInstance().also {
                    it.add(Calendar.DAY_OF_YEAR, -2)
                },
                "Joe Birch",
                image = R.drawable.roxy
            )
        )
    }

}
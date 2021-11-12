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

package academy.compose.messaging.util

import academy.compose.messaging.model.Contact
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle

fun inputShouldTriggerSuggestions(
    contacts: List<Contact>,
    query: String?
) = query != null && query.startsWith('@') &&
    !contacts.any { it.name == stripMentionSymbol(query) }

fun stripMentionSymbol(text: String?) = text?.replace("@", "") ?: ""

fun selectedWord(textState: TextFieldValue): String? {
    val startSelection = textState.selection.start
    var textPosition = 0

    for (currentWord in textState.text.split(" ")) {
        textPosition += currentWord.length + 1
        if (textPosition >= startSelection) return currentWord
    }
    return null
}

fun buildAnnotatedStringWithColors(text: String, color: Color): AnnotatedString {
    val words: List<String> = text.split(" ")

    val builder = AnnotatedString.Builder()
    words.forEachIndexed { index, word ->
        if (word.startsWith("@")) {
            builder.withStyle(style = SpanStyle(color = color)) {
                append(word)
            }
        } else {
            builder.append(word)
        }
        if (index < words.count() - 1) builder.append(" ")
    }
    return builder.toAnnotatedString()
}

fun TextFieldValue.replaceText(
    start: Int,
    end: Int,
    replaceWith: String
): TextFieldValue {
    val newText = this.text.replaceRange(
        start,
        end,
        replaceWith
    )
    val endOfNewWord = start + replaceWith.length
    val range = TextRange(
        endOfNewWord,
        endOfNewWord
    )

    return this.copy(text = newText, selection = range)
}
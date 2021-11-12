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

package academy.compose.authentication

import academy.compose.authentication.model.AuthenticationEvent
import academy.compose.authentication.model.AuthenticationMode
import academy.compose.authentication.model.AuthenticationState
import academy.compose.authentication.model.PasswordRequirement
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel: ViewModel() {

    val uiState = MutableStateFlow(AuthenticationState())

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            AuthenticationEvent.ToggleAuthenticationMode -> toggleAuthenticationMode()
            is AuthenticationEvent.EmailChanged -> {
                updateEmail(authenticationEvent.emailAddress)
            }
            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }
            AuthenticationEvent.Authenticate -> authenticate()
            AuthenticationEvent.ErrorDismissed -> dismissError()
        }
    }

    private fun authenticate() {
        uiState.value = uiState.value.copy(isLoading = true)
        // trigger network request

        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            withContext(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Something went wrong!"
                )
            }
        }
    }

    private fun dismissError() {
        uiState.value = uiState.value.copy(error = null)
    }

    private fun updateEmail(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    private fun updatePassword(password: String) {
        val satisfiedRequirements = mutableListOf<PasswordRequirement>()
        if (password.length > 7) {
            satisfiedRequirements.add(PasswordRequirement.EIGHT_CHARACTERS)
        }
        if (password.any { it.isDigit() }) {
            satisfiedRequirements.add(PasswordRequirement.NUMBER)
        }
        if (password.any { it.isUpperCase() }) {
            satisfiedRequirements.add(PasswordRequirement.CAPITAL_LETTER)
        }

        uiState.value = uiState.value.copy(
            password = password,
            satisfiedPasswordRequirements = satisfiedRequirements.toList()
        )
    }

    private fun toggleAuthenticationMode() {
        val currentMode = uiState.value.authenticationMode
        val newMode = if (currentMode == AuthenticationMode.SIGN_IN) {
            AuthenticationMode.SIGN_UP
        } else AuthenticationMode.SIGN_IN

        uiState.value = uiState.value.copy(authenticationMode = newMode)
    }

}
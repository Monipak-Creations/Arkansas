package com.monipakcreations.arkansas.feature.auth.presentation.login

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginUiStateTest {

    @Test
    fun canSubmit_requiresBothFields_andNotLoading() {
        assertFalse(LoginUiState().canSubmit)
        assertFalse(LoginUiState(username = "a").canSubmit)
        assertFalse(LoginUiState(username = "a", password = "b", isLoading = true).canSubmit)
        assertTrue(LoginUiState(username = "a", password = "b").canSubmit)
    }
}

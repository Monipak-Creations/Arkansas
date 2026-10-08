package com.monipakcreations.arkansas.feature.auth.presentation.home

import com.monipakcreations.arkansas.feature.auth.domain.model.User

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val user: User) : HomeUiState
    data object LoggedOut : HomeUiState
}

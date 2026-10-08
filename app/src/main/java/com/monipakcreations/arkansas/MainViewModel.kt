package com.monipakcreations.arkansas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monipakcreations.arkansas.feature.auth.domain.usecase.ObserveIsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    observeIsLoggedIn: ObserveIsLoggedInUseCase,
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = observeIsLoggedIn()
        .map<Boolean, MainUiState> { MainUiState.Ready(isLoggedIn = it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainUiState.Loading)
}

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Ready(val isLoggedIn: Boolean) : MainUiState
}

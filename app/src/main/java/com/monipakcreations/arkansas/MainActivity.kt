package com.monipakcreations.arkansas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monipakcreations.arkansas.core.designsystem.theme.ArkansasTheme
import com.monipakcreations.arkansas.feature.products.presentation.navigation.ProductsNavKey
import com.monipakcreations.arkansas.feature.auth.presentation.navigation.LoginNavKey
import com.monipakcreations.arkansas.navigation.ArkansasNavDisplay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.uiState.value is MainUiState.Loading }
        enableEdgeToEdge()

        setContent {
            ArkansasTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val state = uiState
                if (state is MainUiState.Ready) {
                    ArkansasNavDisplay(
                        startKey = if (state.isLoggedIn) ProductsNavKey else LoginNavKey,
                    )
                }
            }
        }
    }
}

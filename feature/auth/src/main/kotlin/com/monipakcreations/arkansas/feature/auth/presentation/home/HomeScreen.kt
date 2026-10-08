package com.monipakcreations.arkansas.feature.auth.presentation.home

import com.monipakcreations.arkansas.feature.auth.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monipakcreations.arkansas.core.designsystem.component.NetworkImage
import com.monipakcreations.arkansas.core.designsystem.theme.ArkansasTheme
import com.monipakcreations.arkansas.feature.auth.domain.model.User

@Composable
fun HomeRoute(
    onLoggedOut: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is HomeUiState.LoggedOut) onLoggedOut()
    }

    HomeScreen(uiState = uiState, onLogoutClick = viewModel::logout)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onLogoutClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.feature_home_title)) },
                actions = {
                    TextButton(onClick = onLogoutClick) {
                        Text(stringResource(R.string.feature_home_logout))
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            when (uiState) {
                is HomeUiState.Success -> UserProfile(uiState.user)
                else -> CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun UserProfile(user: User) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        NetworkImage(
            url = user.imageUrl,
            contentDescription = user.fullName,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
        )
        Text(text = user.fullName, style = MaterialTheme.typography.headlineSmall)
        Text(text = "@${user.username}", style = MaterialTheme.typography.bodyLarge)
        Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ArkansasTheme {
        HomeScreen(
            uiState = HomeUiState.Success(
                User(1, "emilys", "emily.johnson@x.dummyjson.com", "Emily", "Johnson", "female", ""),
            ),
            onLogoutClick = {},
        )
    }
}

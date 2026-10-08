package com.monipakcreations.arkansas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.monipakcreations.arkansas.feature.auth.presentation.home.HomeRoute
import com.monipakcreations.arkansas.feature.auth.presentation.navigation.HomeNavKey
import com.monipakcreations.arkansas.feature.auth.presentation.login.LoginRoute
import com.monipakcreations.arkansas.feature.auth.presentation.navigation.LoginNavKey
import com.monipakcreations.arkansas.feature.products.presentation.list.ProductsRoute
import com.monipakcreations.arkansas.feature.products.presentation.navigation.ProductsNavKey

/** Single Navigation 3 host. The back stack is a plain, state-backed list of [NavKey]s. */
@Composable
fun ArkansasNavDisplay(startKey: NavKey) {
    val backStack = rememberNavBackStack(startKey)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<LoginNavKey> {
                LoginRoute(onLoginSuccess = { backStack.resetTo(ProductsNavKey) })
            }
            entry<ProductsNavKey> {
                ProductsRoute(onProfileClick = { backStack.add(HomeNavKey) })
            }
            entry<HomeNavKey> {
                HomeRoute(onLoggedOut = { backStack.resetTo(LoginNavKey) })
            }
        },
    )
}

/** Clears the stack and shows [key] as the new root. */
private fun MutableList<NavKey>.resetTo(key: NavKey) {
    add(key)
    removeAll { it != key }
}

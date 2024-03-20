package ru.tyurin.siptest.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

enum class Routes {
    Users,
    Video,
    UserDetails
}

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

val NAV_ITEMS = listOf(
    NavItem(
        label = "Пользователи",
        icon = Icons.Default.AccountBox,
        route = Routes.Users.name
    ),
    NavItem(
        label = "Видеоролик",
        icon = Icons.Default.Favorite,
        route = Routes.Video.name
    ),

)
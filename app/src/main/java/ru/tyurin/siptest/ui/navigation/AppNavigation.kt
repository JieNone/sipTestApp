package ru.tyurin.siptest.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.tyurin.siptest.R
import ru.tyurin.siptest.ui.screens.VideoScreen
import ru.tyurin.siptest.ui.screens.users.UserDetailsScreen
import ru.tyurin.siptest.ui.screens.users.UsersScreen
import ru.tyurin.siptest.vm.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navHostController: NavHostController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
                title = {
                    Text(
                        stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },

                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            NavigationBar {

                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                NAV_ITEMS.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navHostController.navigate(navItem.route) {
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        },
        content = {paddingValues ->
            Column {

                NavHost(
                    navController = navHostController,
                    startDestination = Routes.Users.name,
                    modifier = Modifier
                        .padding(paddingValues)
                ) {

                    composable(route = Routes.Users.name ) {
                        UsersScreen(navController = navHostController, sharedViewModel = sharedViewModel)
                    }
                    composable(route = "${Routes.UserDetails.name}/{userId}") {
                        UserDetailsScreen(sharedViewModel = sharedViewModel)
                    }
                    composable(route = Routes.Video.name ) { VideoScreen() }

                }

            }

        }
    )
}
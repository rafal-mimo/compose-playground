package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.main.MainScreen
import com.example.compose.ui.profile.ProfileScreen
import com.example.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(stringResource(id = Routes.routeFromString(currentRoute).title))
                        }
                    )
                },
                bottomBar = {
                    BottomNavigation(navController, Routes.ALL)
                }
            ) {
                BottomNavigationConfiguration(navController)
            }
        }
    }
}

sealed class Routes(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Main : Routes(route = "/main", R.string.navigation_item_main, Icons.Filled.Home)
    object Profile : Routes(route = "/profile", R.string.navigation_item_profile, Icons.Filled.Info)

    companion object {
        val ALL = listOf(Main, Profile)

        fun routeFromString(route: String?) = ALL.find { route == it.route } ?: Main
    }
}

@Composable
private fun BottomNavigationConfiguration(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            MainScreen(
                hiltViewModel(navController.getBackStackEntry(Routes.Main.route))
            )
        }
        composable(Routes.Profile.route) {
            ProfileScreen(
                hiltViewModel(navController.getBackStackEntry(Routes.Profile.route))
            )
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController, routes: List<Routes>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        routes.forEach {
            BottomNavigationItem(
                label = { Text(stringResource(id = it.title)) },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = stringResource(id = it.title))
                },
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

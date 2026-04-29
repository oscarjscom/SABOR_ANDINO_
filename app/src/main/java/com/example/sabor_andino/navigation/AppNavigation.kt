package com.example.sabor_andino.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sabor_andino.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Home.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Menu.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) {
            MenuScreen(navController)
        }

        composable(
            route = Screen.Profile.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) {
            ProfileScreen(navController)
        }

        composable(
            route = Screen.Pedido.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) {
            PedidoScreen(navController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("platoId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            ),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) { backStackEntry ->
            val platoId = backStackEntry.arguments?.getInt("platoId") ?: 0
            DetailScreen(navController, platoId)
        }
    }
}

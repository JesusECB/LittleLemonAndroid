package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(OnboardingDestination.route) {
            OnboardingScreen(navController = navController)
        }
        composable(HomeDestination.route) {
            HomeScreen(navController = navController)
        }
        composable(ProfileDestination.route) {
            ProfileScreen(navController = navController)
        }
    }
}

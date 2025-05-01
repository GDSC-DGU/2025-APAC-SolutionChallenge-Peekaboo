package com.peekaboo.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.peekaboo.navigation.NavRoutes
import com.peekaboo.navigation.onboardingNavGraph

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.OnBoardingGraph.route
            ) {
                onboardingNavGraph(
                    navController = navController
                )
            }
        }
    }
}
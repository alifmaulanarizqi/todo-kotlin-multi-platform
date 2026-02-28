package org.alifmaulanarizqi.todokmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.alifmaulanarizqi.todokmp.presentation.screen.home.HomeScreen
import org.alifmaulanarizqi.todokmp.presentation.screen.task.TaskScreen

@Composable
actual fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(
                navigateToTaskScreen = { taskId ->
                    navController.navigate(route = Screen.Task(taskId))
                }
            )
        }
        composable<Screen.Task> {
            TaskScreen(
                taskId = it.toRoute<Screen.Task>().taskId,
                navigateToBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
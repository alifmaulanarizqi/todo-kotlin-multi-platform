package org.alifmaulanarizqi.todokmp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import org.alifmaulanarizqi.todokmp.presentation.screen.home.HomeScreen
import org.alifmaulanarizqi.todokmp.presentation.screen.task.TaskScreen
import org.koin.compose.koinInject

@Composable
actual fun NavGraph() {
    val navigator = koinInject<Navigator>()

    NavDisplay(
        backStack = navigator.backStack,
        onBack = { navigator.goBack() },
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeScreen(
                    navigateToTaskScreen = { taskId ->
                        navigator.navigateTo(Screen.Task(taskId))
                    }
                )
            }
            entry<Screen.Task> {
                TaskScreen(
                    taskId = it.id,
                    navigateToBack = {
                        navigator.goBack()
                    }
                )
            }
        }
    )
}
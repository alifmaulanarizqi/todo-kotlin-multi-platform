package org.alifmaulanarizqi.todokmp.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class Navigator {
    val backStack: SnapshotStateList<Screen> = mutableStateListOf(Screen.Home)

    fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    fun navigateToTask(taskId: String? = null) {
        if(backStack.lastOrNull() is Screen.Task) {
            backStack[backStack.lastIndex] = Screen.Task(
                taskId = taskId
            )
        } else {
            backStack.add(Screen.Task(taskId))
        }
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }
}
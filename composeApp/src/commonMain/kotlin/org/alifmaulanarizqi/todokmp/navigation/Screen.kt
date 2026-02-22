package org.alifmaulanarizqi.todokmp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Home: Screen()

    @Serializable
    data class Task(
        val taskId: String? = null,
    ): Screen()
}
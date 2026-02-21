package org.alifmaulanarizqi.todokmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.alifmaulanarizqi.todokmp.navigation.NavGraph
import org.alifmaulanarizqi.todokmp.util.darkScheme
import org.alifmaulanarizqi.todokmp.util.lightScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val colorSchema = if(isSystemInDarkTheme()) darkScheme else lightScheme
    MaterialTheme(
        colorScheme = colorSchema
    ) {
        NavGraph()
    }
}
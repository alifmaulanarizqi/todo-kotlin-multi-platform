package org.alifmaulanarizqi.todokmp

import androidx.compose.ui.window.ComposeUIViewController
import org.alifmaulanarizqi.todokmp.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }
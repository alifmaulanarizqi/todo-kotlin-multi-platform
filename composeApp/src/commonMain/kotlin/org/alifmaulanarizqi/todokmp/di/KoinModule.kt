package org.alifmaulanarizqi.todokmp.di

import org.alifmaulanarizqi.todokmp.data.FakeToDoRepository
import org.alifmaulanarizqi.todokmp.data.ToDoRepository
import org.alifmaulanarizqi.todokmp.navigation.Navigator
import org.alifmaulanarizqi.todokmp.presentation.screen.home.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinModule = module {
    singleOf(constructor = ::Navigator)
    single<ToDoRepository> { FakeToDoRepository() }
    viewModelOf(::HomeViewModel)
}

fun initializeKoin(
    config: (KoinApplication. () -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(
            koinModule
        )
    }
}
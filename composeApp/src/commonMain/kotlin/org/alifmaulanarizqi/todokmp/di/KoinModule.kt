package org.alifmaulanarizqi.todokmp.di

import org.alifmaulanarizqi.todokmp.data.FakeToDoRepository
import org.alifmaulanarizqi.todokmp.data.ToDoRepository
import org.alifmaulanarizqi.todokmp.data.ToDoRepositoryImpl
import org.alifmaulanarizqi.todokmp.navigation.Navigator
import org.alifmaulanarizqi.todokmp.presentation.screen.home.HomeViewModel
import org.alifmaulanarizqi.todokmp.presentation.screen.task.TaskViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val targetModule: Module

val koinModule = module {
    singleOf(constructor = ::Navigator)
//    single<ToDoRepository> { FakeToDoRepository() }
    single<ToDoRepository> { ToDoRepositoryImpl(get()) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::TaskViewModel)
}

fun initializeKoin(
    config: (KoinApplication. () -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(
            koinModule,
            targetModule
        )
    }
}
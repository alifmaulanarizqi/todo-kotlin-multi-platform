package org.alifmaulanarizqi.todokmp.di

import org.alifmaulanarizqi.todokmp.data.DatabaseDriverFactory
import org.alifmaulanarizqi.todokmp.data.IosDatabaseDriverFactory
import org.koin.dsl.module

actual val targetModule = module {
    single<DatabaseDriverFactory> { IosDatabaseDriverFactory() }
}
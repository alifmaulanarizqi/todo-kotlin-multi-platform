package org.alifmaulanarizqi.todokmp.di

import org.alifmaulanarizqi.todokmp.data.AndroidDatabaseDriverFactory
import org.alifmaulanarizqi.todokmp.data.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext

import org.koin.dsl.module

actual val targetModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
}
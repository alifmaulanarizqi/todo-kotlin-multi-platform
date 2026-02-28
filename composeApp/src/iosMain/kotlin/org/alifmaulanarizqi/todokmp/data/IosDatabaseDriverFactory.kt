package org.alifmaulanarizqi.todokmp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.alifmaulanarizqi.TaskDatabase

class IosDatabaseDriverFactory: DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = TaskDatabase.Schema,
            name = "task.db"
        )
    }
}
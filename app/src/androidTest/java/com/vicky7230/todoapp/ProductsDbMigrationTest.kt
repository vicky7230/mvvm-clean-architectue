package com.vicky7230.todoapp

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vicky7230.todoapp.data.local.ProductsDb
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsDbMigrationTest {

    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        ProductsDb::class.java,
        emptyList(),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migrateFrom1To2_shouldMatchSchema() {
        // Create the database as it existed in version 1
        helper.createDatabase(TEST_DB, 1).close()

        // Run the auto migration and validate against exported schema
        helper.runMigrationsAndValidate(
            TEST_DB,
            2,
            true // validate against Room's expected schema
        )
    }

    @Test
    fun migrateFrom2To3_shouldMatchSchema() {
        helper.createDatabase(TEST_DB, 2).close()

        helper.runMigrationsAndValidate(
            TEST_DB,
            3,
            true
        )
    }

    @Test
    fun migrateFrom1To3_shouldMatchSchema() {
        helper.createDatabase(TEST_DB, 1).close()

        helper.runMigrationsAndValidate(
            TEST_DB,
            3,
            true
        )
    }
}

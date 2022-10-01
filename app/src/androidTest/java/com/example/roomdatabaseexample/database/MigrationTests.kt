package com.example.roomdatabaseexample.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.roomdatabaseexample.data.localDB.WordDatabase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MigrationTests {

    companion object {
        private const val DB_NAME = "Test_DB"
    }


    private  lateinit var database:  SupportSQLiteDatabase

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()

    @get:Rule
    val migrationTestHelper =
        MigrationTestHelper(InstrumentationRegistry.getInstrumentation(), WordDatabase::class.java)

    @Test
    fun test_migration_1_to_2() {

        // Create database with version 1 and add some dummy data
        database = migrationTestHelper.createDatabase(DB_NAME, 1).apply {
            execSQL("INSERT INTO word_table VALUES(1, 'Stars')")
            execSQL("INSERT INTO word_table VALUES(2, 'Apple')")
            close()
        }

        // Run the db migration
        database = migrationTestHelper.runMigrationsAndValidate(DB_NAME,2, true, WordDatabase.MIGRATION_1_2)

        // verify if the data is valid.
        val dbCursor = database.query("SELECT * FROM word_table")
        // Cursor should move to first item as we have some data inserted in version 1
        Assert.assertTrue(dbCursor.moveToFirst())

        // Verify if the values are same.
        Assert.assertEquals(1,dbCursor.getInt(dbCursor.getColumnIndex("id")))

    }
}
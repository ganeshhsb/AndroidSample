package com.nova.coroutinesample

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nova.coroutinesample.user.di.activityModules
import com.nova.coroutinesample.user.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppApplication : Application() {
    companion object {
        private lateinit var appApplication: Application
        fun getApplication() =
            appApplication
    }

    lateinit var database: AppDatabase
    override fun onCreate() {
        super.onCreate()
        appApplication = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "AppDatabase")
            .addMigrations(MIGRATION_1_2)
            .build()

        startKoin {
            androidContext(this@AppApplication)
            modules(appModules + activityModules)
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
    }
}

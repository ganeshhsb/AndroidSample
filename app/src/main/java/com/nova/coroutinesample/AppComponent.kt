package com.nova.coroutinesample

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getApplication(): Application
    fun getDatabase(): AppDatabase
    fun getUserDao():UserDao
    fun inject(model: MainActivityModel)
    fun inject(repo: UserRepo)
}

@Module
class AppModule {
    @Provides
    fun getApplication(): Application = AppApplication.getApplication()

    @Provides
    fun getDatabase(): AppDatabase = (AppApplication.getApplication() as AppApplication).database

    @Provides
    fun getUserDao() = getDatabase().getUserDao()
}
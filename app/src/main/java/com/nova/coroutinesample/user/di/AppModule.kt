package com.nova.coroutinesample.user.di

import com.nova.coroutinesample.AppDatabase
import com.nova.coroutinesample.user.repo.UserRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


private val dataModule = module {

    single { AppDatabase.getInstance(androidContext()) }

    single { get<AppDatabase>().getUserDao() }
    single { UserRepo() }
}


val appModules = listOf(dataModule)
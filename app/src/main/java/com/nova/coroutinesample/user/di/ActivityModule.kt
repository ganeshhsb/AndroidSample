package com.nova.coroutinesample.user.di

import com.nova.coroutinesample.user.viewmodel.UserListModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val userListModule = module {
    viewModel { UserListModel() }
}


val activityModules = listOf(userListModule)
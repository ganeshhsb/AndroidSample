//package com.nova.coroutinesample.user.di
//
//import android.app.Application
//import com.nova.coroutinesample.AppApplication
//import com.nova.coroutinesample.AppDatabase
//import com.nova.coroutinesample.user.viewmodel.UserListModel
//import com.nova.coroutinesample.user.db.UserDao
//import com.nova.coroutinesample.user.repo.UserRepo
//import dagger.Component
//import dagger.Module
//import dagger.Provides
//
//@Component(modules = [AppModule::class])
//interface AppComponent {
//    fun getApplication(): Application
//    fun getDatabase(): AppDatabase
//    fun getUserDao(): UserDao
//    fun inject(model: UserListModel)
//    fun inject(repo: UserRepo)
//}
//
//@Module
//class AppModule {
//    @Provides
//    fun getApplication(): Application =
//        AppApplication.getApplication()
//
//    @Provides
//    fun getDatabase(): AppDatabase = (AppApplication.getApplication() as AppApplication).database
//
//    @Provides
//    fun getUserDao() = getDatabase().getUserDao()
//}
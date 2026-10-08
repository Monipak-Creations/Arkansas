package com.monipakcreations.arkansas.core.database.di

import android.content.Context
import androidx.room.Room
import com.monipakcreations.arkansas.core.database.ArkansasDatabase
import com.monipakcreations.arkansas.core.database.dao.ProductDao
import com.monipakcreations.arkansas.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArkansasDatabase =
        Room.databaseBuilder(context, ArkansasDatabase::class.java, "arkansas.db").fallbackToDestructiveMigration(dropAllTables = true).build()

    @Provides
    fun provideUserDao(database: ArkansasDatabase): UserDao = database.userDao()

    @Provides
    fun provideProductDao(database: ArkansasDatabase): ProductDao = database.productDao()
}

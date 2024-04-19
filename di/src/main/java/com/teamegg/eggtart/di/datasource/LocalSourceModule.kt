package com.teamegg.eggtart.di.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.teamegg.eggtart.core.room.database.AppDatabase
import com.teamegg.eggtart.core.room.database.dao.MandalartCellDao
import com.teamegg.eggtart.core.room.database.dao.MandalartSheetDao
import com.teamegg.eggtart.core.room.source.MandalartCellLocalSource
import com.teamegg.eggtart.core.room.source.MandalartCellLocalSourceImpl
import com.teamegg.eggtart.core.room.source.MandalartSheetLocalSource
import com.teamegg.eggtart.core.room.source.MandalartSheetLocalSourceImpl
import com.teamegg.eggtart.datastore.user.datasource.UserLocalSource
import com.teamegg.eggtart.datastore.user.datasource.UserLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/04/09
 **/

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceProvidesModule {
    @Singleton
    private val Context.createDataStore: DataStore<Preferences> by preferencesDataStore("eggtart_pref")

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "EggtartDatabase.db").build()

    @Singleton
    @Provides
    fun provideMandalartCellDao(appDatabase: AppDatabase): MandalartCellDao = appDatabase.mandalartCellDao()

    @Singleton
    @Provides
    fun provideMandalartSheetDao(appDatabase: AppDatabase): MandalartSheetDao = appDatabase.mandalartSheetDao()

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = context.createDataStore
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceBindsModule {
    @Singleton
    @Binds
    abstract fun bindsMandalartCellLocalSource(mandalartCellLocalSourceImpl: MandalartCellLocalSourceImpl): MandalartCellLocalSource

    @Singleton
    @Binds
    abstract fun bindsMandalartSheetLocalSource(mandalartSheetLocalSourceImpl: MandalartSheetLocalSourceImpl): MandalartSheetLocalSource

    @Singleton
    @Binds
    abstract fun bindsUserLocalSource(userLocalSourceImpl: UserLocalSourceImpl): UserLocalSource
}
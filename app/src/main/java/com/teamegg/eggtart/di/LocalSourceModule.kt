package com.teamegg.eggtart.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.teamegg.eggtart.data.database.AppDatabase
import com.teamegg.eggtart.data.database.dao.MandalartCellDao
import com.teamegg.eggtart.data.database.dao.MandalartSheetDao
import com.teamegg.eggtart.data.source.local.MandalartCellLocalSource
import com.teamegg.eggtart.data.source.local.MandalartCellLocalSourceImpl
import com.teamegg.eggtart.data.source.local.MandalartSheetLocalSource
import com.teamegg.eggtart.data.source.local.MandalartSheetLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/03/21
 **/

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "MandalartDatabase.db").build()

    @Singleton
    @Provides
    fun provideMandalartCellDao(appDatabase: AppDatabase): MandalartCellDao = appDatabase.mandalartCellDao()

    @Singleton
    @Provides
    fun provideMandalartSheetDao(appDatabase: AppDatabase): MandalartSheetDao = appDatabase.mandalartSheetDao()

    @Singleton
    @Provides
    fun provideMandalartCellLocalSource(mandalartCellDao: MandalartCellDao): MandalartCellLocalSource = MandalartCellLocalSourceImpl(mandalartCellDao)

    @Singleton
    @Provides
    fun provideMandalartSheetLocalSource(mandalartSheetDao: MandalartSheetDao): MandalartSheetLocalSource = MandalartSheetLocalSourceImpl(mandalartSheetDao)
}
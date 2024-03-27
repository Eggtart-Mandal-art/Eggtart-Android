package com.eggtart.eggtart.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.eggtart.eggtart.database.AppDatabase
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
}
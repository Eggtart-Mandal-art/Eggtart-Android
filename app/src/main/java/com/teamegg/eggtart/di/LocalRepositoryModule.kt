package com.teamegg.eggtart.di

import com.teamegg.eggtart.data.repository.local.MandalartCellRepositoryImpl
import com.teamegg.eggtart.data.repository.local.MandalartSheetRepositoryImpl
import com.teamegg.eggtart.data.source.local.MandalartCellLocalSource
import com.teamegg.eggtart.data.source.local.MandalartSheetLocalSource
import com.teamegg.eggtart.domain.repository.local.MandalartCellRepository
import com.teamegg.eggtart.domain.repository.local.MandalartSheetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/03/21
 **/

@Module
@InstallIn(SingletonComponent::class)
object LocalRepositoryModule {

    @Provides
    @Singleton
    fun provideMandalartCellRepository(mandalartCellLocalSource: MandalartCellLocalSource): MandalartCellRepository = MandalartCellRepositoryImpl(mandalartCellLocalSource)

    @Provides
    @Singleton
    fun provideMandalartSheetRepository(mandalartSheetLocalSource: MandalartSheetLocalSource): MandalartSheetRepository = MandalartSheetRepositoryImpl(mandalartSheetLocalSource)
}
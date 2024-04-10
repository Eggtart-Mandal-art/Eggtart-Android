package com.teamegg.eggtart.di.repository

import com.teamegg.eggtart.core.room.repository.MandalartCellRepositoryImpl
import com.teamegg.eggtart.core.room.repository.MandalartSheetRepositoryImpl
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import com.teamegg.eggtart.domain.mandalart.repository.MandalartSheetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/04/09
 **/

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsMandalartCellRepository(mandalartCellRepositoryImpl: MandalartCellRepositoryImpl): MandalartCellRepository

    @Singleton
    @Binds
    abstract fun bindsMandalartSheetRepository(mandalartSheetRepositoryImpl: MandalartSheetRepositoryImpl): MandalartSheetRepository
}
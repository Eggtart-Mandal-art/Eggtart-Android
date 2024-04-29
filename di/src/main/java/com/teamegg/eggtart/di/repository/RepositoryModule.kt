package com.teamegg.eggtart.di.repository

import com.teamegg.eggtart.core.network.mandalart.repository.MandalartRemoteRepositoryImpl
import com.teamegg.eggtart.core.network.user.repository.NetworkUserRepositoryImpl
import com.teamegg.eggtart.core.room.repository.MandalartCellRepositoryImpl
import com.teamegg.eggtart.core.room.repository.MandalartSheetRepositoryImpl
import com.teamegg.eggtart.datastore.user.repository.LocalUserRepositoryImpl
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import com.teamegg.eggtart.domain.mandalart.repository.MandalartSheetRepository
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import com.teamegg.eggtart.domain.user.repository.LocalUserRepository
import com.teamegg.eggtart.domain.user.repository.NetworkUserRepository
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
abstract class RepositoryBindsModule {

    @Singleton
    @Binds
    abstract fun bindsMandalartCellRepository(mandalartCellRepositoryImpl: MandalartCellRepositoryImpl): MandalartCellRepository

    @Singleton
    @Binds
    abstract fun bindsMandalartSheetRepository(mandalartSheetRepositoryImpl: MandalartSheetRepositoryImpl): MandalartSheetRepository

    @Singleton
    @Binds
    abstract fun bindsNetworkUserRepository(networkUserRepositoryImpl: NetworkUserRepositoryImpl): NetworkUserRepository

    @Singleton
    @Binds
    abstract fun bindsLocalUserRepository(localUserRepositoryImpl: LocalUserRepositoryImpl): LocalUserRepository

    @Singleton
    @Binds
    abstract fun bindsMandalartRemoteRepository(mandalartRemoteRepositoryImpl: MandalartRemoteRepositoryImpl): MandalartRemoteRepository
}
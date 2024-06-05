package com.baker.eggtart.di.repository

import com.baker.eggtart.core.network.mandalart.repository.MandalartRemoteRepositoryImpl
import com.baker.eggtart.core.network.user.repository.NetworkUserRepositoryImpl
import com.baker.eggtart.core.room.repository.MandalartCellRepositoryImpl
import com.baker.eggtart.core.room.repository.MandalartSheetRepositoryImpl
import com.baker.eggtart.datastore.user.repository.LocalUserRepositoryImpl
import com.baker.eggtart.domain.mandalart.repository.MandalartCellRepository
import com.baker.eggtart.domain.mandalart.repository.MandalartSheetRepository
import com.baker.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import com.baker.eggtart.domain.user.repository.LocalUserRepository
import com.baker.eggtart.domain.user.repository.NetworkUserRepository
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
package com.baker.eggtart.di.repository

import com.baker.eggtart.core.network.kakao.repository.KakaoRepositoryImpl
import com.baker.eggtart.domain.kakao.repository.KakaoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 *  Created by wonjin on 2024/04/11
 **/

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryActivityModule {

    @Binds
    @ActivityScoped
    abstract fun bindsKakaoRepository(kakaoRepositoryImpl: KakaoRepositoryImpl): KakaoRepository
}
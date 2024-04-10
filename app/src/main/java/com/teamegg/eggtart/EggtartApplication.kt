package com.teamegg.eggtart

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by 노원진 on 2024.03.21
 */

@HiltAndroidApp
class EggtartApplication : Application() {
    init {
        application = this
    }

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(applicationContext, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

    companion object {
        lateinit var application: Application
        fun applicationContext(): Context = application.applicationContext
    }
}
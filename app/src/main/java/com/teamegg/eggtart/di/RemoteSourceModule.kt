package com.teamegg.eggtart.di

import com.teamegg.eggtart.domain.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/03/21
 **/

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {
    @Provides
    fun provideBaseUrl(): String = ""

    @Provides
    fun provideTimeOut(): Long = 10L

    @Provides
    @Singleton
    fun provideOkHttpClient(timeOut: Long): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(timeOut, TimeUnit.SECONDS)
        readTimeout(timeOut, TimeUnit.SECONDS)
        writeTimeout(timeOut, TimeUnit.SECONDS)
        addInterceptor(
            HttpLoggingInterceptor { message ->
                Log.d("EGGTART_SERVER", message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
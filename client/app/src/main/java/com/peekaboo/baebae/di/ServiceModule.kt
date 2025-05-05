package com.peekaboo.baebae.di

import com.peekaboo.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideAuthService(@BaeBaeRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}
package com.peekaboo.baebae.di

import com.peekaboo.data.service.AuthService
import com.peekaboo.data.service.CrawlService
import com.peekaboo.data.service.DiagnosisService
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

    @Provides
    fun provideDiagnosisService(@BaeBaeRetrofit retrofit: Retrofit): DiagnosisService {
        return retrofit.create(DiagnosisService::class.java)
    }

    @Provides
    fun provideCrawlingService(@CrawlRetrofit retrofit: Retrofit): CrawlService {
        return retrofit.create(CrawlService::class.java)
    }
}
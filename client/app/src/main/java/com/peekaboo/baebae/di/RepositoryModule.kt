package com.peekaboo.baebae.di

import com.peekaboo.data.repositoryImpl.AuthRepositoryImpl
import com.peekaboo.data.repositoryImpl.CrawlRepositoryImpl
import com.peekaboo.data.repositoryImpl.DiagnosisRepositoryImpl
import com.peekaboo.domain.repository.AuthRepository
import com.peekaboo.domain.repository.CrawlRepository
import com.peekaboo.domain.repository.DiagnosisRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsDiagnosisRepository(repositoryImpl: DiagnosisRepositoryImpl): DiagnosisRepository

    @Binds
    @Singleton
    abstract fun bindsCrawlRepository(repositoryImpl: CrawlRepositoryImpl): CrawlRepository
}
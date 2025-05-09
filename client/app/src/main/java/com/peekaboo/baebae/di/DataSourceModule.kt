package com.peekaboo.baebae.di

import com.peekaboo.data.dataSource.AuthDataSource
import com.peekaboo.data.dataSource.CrawlDataSource
import com.peekaboo.data.dataSource.DiagnosisDataSource
import com.peekaboo.data.dataSource.dataSourceImpl.AuthDataSourceImpl
import com.peekaboo.data.dataSource.dataSourceImpl.CrawlDataSourceImpl
import com.peekaboo.data.dataSource.dataSourceImpl.DiagnosisDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindsDiagnosisDataSource(diagnosisDataSourceImpl: DiagnosisDataSourceImpl): DiagnosisDataSource

    @Binds
    @Singleton
    abstract fun bindsCrawlDataSource(crawlDataSourceImpl: CrawlDataSourceImpl): CrawlDataSource
}
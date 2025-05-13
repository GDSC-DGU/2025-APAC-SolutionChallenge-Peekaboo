package com.peekaboo.baebae.di

import com.peekaboo.baebae.BuildConfig
import com.peekaboo.core.util.isJsonArray
import com.peekaboo.core.util.isJsonObject
import com.peekaboo.data.dataStore.LocalDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val json = Json { ignoreUnknownKeys = true }
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val BEARER = "Bearer "
    private const val AUTHORIZATION = "Authorization"

    @Singleton
    @Provides
    @BaeBaeRetrofit
    fun providesAuthInterceptor(
        localDataSource: LocalDataStore
    ): Interceptor = Interceptor { chain ->
        val tokenFlow = localDataSource.accessToken
        val token = runBlocking { tokenFlow.first() }

        val request = chain.request()
        val response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, BEARER + token)
                .build()
        )
        return@Interceptor response
    }

    @Singleton
    @Provides
    @CrawlRetrofit
    fun providesCrawlInterceptor(
        localDataSource: LocalDataStore
    ): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build()
        )
        return@Interceptor response
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Timber.tag("retrofit").d(JSONObject(message).toString(4))

                message.isJsonArray() ->
                    Timber.tag("retrofit").d(JSONArray(message).toString(4))

                else -> {
                    Timber.tag("retrofit").d("CONNECTION INFO -> $message")
                }
            }
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return loggingInterceptor
    }

    @Singleton
    @Provides
    @BaeBaeRetrofit
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @BaeBaeRetrofit interceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    @CrawlRetrofit
    fun provideCrawlOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @CrawlRetrofit interceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()


    @Provides
    @Singleton
    @BaeBaeRetrofit
    fun providesRetrofit(
        @BaeBaeRetrofit okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

    @Provides
    @Singleton
    @CrawlRetrofit
    fun providesCrawlRetrofit(
        @CrawlRetrofit okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.CRAWL_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

}
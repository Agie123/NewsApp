package com.example.newsapp.core.data.di

import com.example.newsapp.core.feature.top_headlines.data.datasource.TopHeadlinesRemoteDataSourceImpl
import com.example.newsapp.core.feature.top_headlines.domain.datasource.TopHeadlinesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindTopHeadlinesRemoteDataSource(
        topHeadlinesRemoteDataSourceImpl: TopHeadlinesRemoteDataSourceImpl,
    ): TopHeadlinesRemoteDataSource
}
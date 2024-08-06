package com.android.ai.aitutor.di

import com.android.ai.aitutor.data.repositoryImpl.RepositoryImpl
import com.android.ai.aitutor.domain.repository.Repository
import com.android.ai.aitutor.domain.usecases.SendPromptUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRepository() : Repository{
        return RepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideSendPromptUseCase(repository: Repository) : SendPromptUseCase {
        return SendPromptUseCase(repository)
    }

}
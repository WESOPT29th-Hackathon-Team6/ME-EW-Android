package com.kangmin.meew.di

import com.example.domain.repository.LocalRepository
import com.example.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
        @Named("SharedPref") sharedPrefRepo: LocalRepository
    ): LoginUseCase = LoginUseCase(sharedPrefRepo)
}
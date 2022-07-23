package com.kangmin.meew.di

import com.example.domain.repository.CharacterRepository
import com.example.domain.repository.LocalRepository
import com.example.domain.repository.LoginRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.CharacterUseCase
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignUpUseCase
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
        @Named("SharedPref") sharedPrefRepo: LocalRepository,
        loginRepository: LoginRepository
    ): LoginUseCase = LoginUseCase(sharedPrefRepo, loginRepository)

    @Singleton
    @Provides
    fun provideSignUpUseCase(
        @Named("User")userRepo: UserRepository,
        @Named("Character")characterRepo: CharacterRepository
    ): SignUpUseCase = SignUpUseCase(userRepo, characterRepo)

    @Singleton
    @Provides
    fun provideCharacterUseCase(
        @Named("Character")characterRepo: CharacterRepository
    ): CharacterUseCase = CharacterUseCase(characterRepo)
}
package com.example.tasktrack.core.di

import com.example.tasktrack.login.domain.usecase.CredentialLoginUseCase
import com.example.tasktrack.login.domain.usecase.ProdCredentialsLoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class useCaseModule {

    @Binds
    abstract fun bindCredentialsLoginUseCase(credentialsLoginUseCase: ProdCredentialsLoginUseCase): CredentialLoginUseCase
}

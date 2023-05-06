package com.example.tasktrack.core.di

import com.example.tasktrack.login.domain.repository.DemoLoginService
import com.example.tasktrack.login.domain.repository.DemoTokenService
import com.example.tasktrack.login.domain.repository.LoginRepository
import com.example.tasktrack.login.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTokenService(tokenRepository: DemoTokenService): TokenRepository

    @Binds
    abstract fun bindLoginRepository(tokenRepository: DemoLoginService): LoginRepository
}

package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.remote.AuthRemoteDataSource
import org.sopt.dosopttemplate.data.datasource.remote.impl.AuthRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun providesAuthDataSource(DataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}

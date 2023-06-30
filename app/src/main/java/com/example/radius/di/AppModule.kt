package com.example.radius.di

import com.example.radius.data.datasource.local.RealmDataSource
import com.example.radius.data.datasource.local.RealmDataSourceImpl
import com.example.radius.data.datasource.remote.RemoteDataSource
import com.example.radius.data.datasource.remote.RemoteDataSourceImpl
import com.example.radius.data.remote.FacilitiesAPI
import com.example.radius.data.repository.FacilityRepository
import com.example.radius.data.util.BASE_URL
import com.example.radius.domain.repository.FacilityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFacilitiesApiService(retrofit: Retrofit): FacilitiesAPI {
        return retrofit.create(FacilitiesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(dataSource: RemoteDataSourceImpl): RemoteDataSource = dataSource

    @Provides
    @Singleton
    fun provideRealmDataSource(dataSource: RealmDataSourceImpl): RealmDataSource = dataSource

    @Provides
    @Singleton
    fun provideFacilityRepository(repository: FacilityRepositoryImpl): FacilityRepository = repository
}

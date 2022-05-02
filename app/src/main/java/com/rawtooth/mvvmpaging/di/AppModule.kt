package com.rawtooth.mvvmpaging.di

import com.rawtooth.mvvmpaging.network.MyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit()=Retrofit.Builder().baseUrl(MyApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(MyApi::class.java)!!
}
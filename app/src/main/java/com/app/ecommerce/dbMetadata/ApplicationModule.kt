package com.app.ecommerce.dbMetadata

import com.app.ecommerce.network.ApiInterface
import com.app.ecommerce.ui.activity.usecase.LoginUseCase
import com.app.ecommerce.ui.activity.usecase.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private const val BASE_URL = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()

    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun provideLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase {
        return loginUseCaseImpl
    }
}

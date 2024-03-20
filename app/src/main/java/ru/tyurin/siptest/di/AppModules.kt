package ru.tyurin.siptest.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.tyurin.siptest.domain.models.UsersRepository
import ru.tyurin.siptest.network.UsersApi
import ru.tyurin.siptest.network.repo.UsersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): UsersApi = retrofit.create(UsersApi::class.java)

    @Provides
    fun provideUsersRepository(
        api: UsersApi
    ): UsersRepository = UsersRepositoryImpl(
        apiService = api
    )
}

package ru.tyurin.siptest.network

import retrofit2.http.GET
import ru.tyurin.siptest.domain.models.User


interface UsersApi {
    @GET("/users")
    suspend fun getUsers(
    ): List<User>
}
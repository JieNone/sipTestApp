package ru.tyurin.siptest.network.repo

import ru.tyurin.siptest.domain.models.User
import ru.tyurin.siptest.domain.models.UsersRepository
import ru.tyurin.siptest.network.UsersApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val apiService: UsersApi
) : UsersRepository {

    override suspend fun loadUsers(): List<User> {
        return apiService.getUsers()
    }
}
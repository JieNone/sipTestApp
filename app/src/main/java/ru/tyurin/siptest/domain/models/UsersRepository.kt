package ru.tyurin.siptest.domain.models

interface UsersRepository {

    suspend fun loadUsers() : List<User>
}

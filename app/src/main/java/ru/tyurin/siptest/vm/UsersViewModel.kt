package ru.tyurin.siptest.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.tyurin.siptest.domain.models.User
import ru.tyurin.siptest.domain.models.UsersRepository
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepository,
) : ViewModel() {

    private val _usersList = MutableStateFlow<List<User>>(emptyList())
    val usersList = _usersList.asStateFlow()

    init {
        fetchUsers()
    }
    fun fetchUsers() {
        viewModelScope.launch {
            _usersList.value = repository.loadUsers()
        }
    }
}

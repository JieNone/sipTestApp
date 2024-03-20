package ru.tyurin.siptest.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.tyurin.siptest.domain.models.User
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor()
    : ViewModel()
{
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser
    fun selectUser(user: User) {
        _selectedUser.value = user
    }
}
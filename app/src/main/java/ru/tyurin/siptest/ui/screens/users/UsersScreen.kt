package ru.tyurin.siptest.ui.screens.users


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import ru.tyurin.siptest.domain.models.User
import ru.tyurin.siptest.ui.navigation.Routes
import ru.tyurin.siptest.vm.SharedViewModel
import ru.tyurin.siptest.vm.UsersViewModel

@Composable
fun UserItem(user: User, onUserClick: (User) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUserClick(user) }
            .padding(16.dp)
    ) {
        Text(text = user.name)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = user.email)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = user.phone)
    }
}

@Composable
fun UsersScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val viewModel: UsersViewModel = hiltViewModel()
    val users by viewModel.usersList.collectAsStateWithLifecycle()
    LazyColumn {
        items(users) { user ->
            UserItem(user) {
                sharedViewModel.selectUser(user)
                navController.navigate("${Routes.UserDetails.name}/${user.id}")
            }
            HorizontalDivider()
        }
    }
}
package ru.tyurin.siptest.ui.screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.tyurin.siptest.vm.SharedViewModel


@Composable
fun UserDetailsScreen(
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val user by sharedViewModel.selectedUser.collectAsStateWithLifecycle()

    user?.let { element ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${element.name}")
            Text(text = "Username: ${element.username}")
            Text(text = "Email: ${element.email}")
            Text(text = "Address:")
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = "Street: ${element.address.street}")
                Text(text = "Suite: ${element.address.suite}")
                Text(text = "City: ${element.address.city}")
                Text(text = "Zipcode: ${element.address.zipcode}")
                Text(text = "Geo: Lat: ${element.address.geo.lat}, Lng: ${element.address.geo.lng}")
            }
            Text(text = "Phone: ${element.phone}")
            Text(text = "Website: ${element.website}")
            Text(text = "Company:")
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = "Name: ${element.company.name}")
                Text(text = "Catch Phrase: ${element.company.catchPhrase}")
                Text(text = "BS: ${element.company.bs}")
            }
        }
    }

}
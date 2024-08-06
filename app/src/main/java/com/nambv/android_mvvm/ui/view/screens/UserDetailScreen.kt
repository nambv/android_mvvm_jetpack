package com.nambv.android_mvvm.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nambv.android_mvvm.data.model.Name
import com.nambv.android_mvvm.data.model.Picture
import com.nambv.android_mvvm.data.model.User
import com.nambv.android_mvvm.ui.theme.MyAppTheme

@Composable
fun UserDetailScreen(user: User) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user.picture!!.large,
                contentDescription = "Cover Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = user.picture.thumbnail,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = user.name!!.last, style = MaterialTheme.typography.h5)

            Spacer(modifier = Modifier.height(16.dp))

            DetailRow(label = "Email", value = user.email)
            DetailRow(label = "Phone", value = user.phone)
            DetailRow(label = "Gender", value = user.gender)
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    MyAppTheme {
        UserDetailScreen(
            user = User(
                name = Name(last = "Nam"),
                email = "john.doe@example.com",
                phone = "+123456789",
                gender = "Male",
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/men/75.jpg",
                    medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg",
                )
            )
        )
    }
}
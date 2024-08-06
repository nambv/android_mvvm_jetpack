package com.nambv.android_mvvm.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nambv.android_mvvm.R
import com.nambv.android_mvvm.data.ViewState
import com.nambv.android_mvvm.data.model.User

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersScreen(
    navController: NavController, viewModel: UsersViewModel = hiltViewModel()
) {
    val items by viewModel.items.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val state by viewModel.state.collectAsState()

    val listState = rememberLazyListState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        viewModel.refreshItems()
    })

    val isLoadingMore = remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex == items.size - 1
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("User List") }, actions = {
            IconButton(onClick = { /* Handle actions here */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is ViewState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is ViewState.Success -> {
                    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                            items(items) { item ->
                                UserInfoCard(user = item) {
                                    navController.navigate("user_detail/${item.toString()}")
                                }
                            }
                        }

                        PullRefreshIndicator(
                            refreshing = isRefreshing, state = pullRefreshState, Modifier.align(
                                Alignment.TopCenter
                            )
                        )
                    }
                }

                is ViewState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = (state as ViewState.Error).message,
                            color = MaterialTheme.colors.error
                        )
                    }
                }
            }
        }
    }

    if (isLoadingMore.value) {
        LaunchedEffect(Unit) {
            viewModel.fetchItems()
        }
    }
}

@Composable
fun UserInfoCard(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(user.picture!!.thumbnail)
                    .crossfade(true).placeholder(R.drawable.ic_launcher_background).build(),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 4.dp)
                    .weight(weight = 1f)
            ) {
                Text(
                    text = user.name!!.last,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = user.email, fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = user.phone, fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = user.gender, fontSize = 16.sp
                )
            }
        }
    }
}
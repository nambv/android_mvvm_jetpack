package com.nambv.android_mvvm.ui.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersListView(viewModel: UsersViewModel = hiltViewModel()) {
    val items by viewModel.items.observeAsState(initial = emptyList())
    val isRefreshing by viewModel.isRefreshing.observeAsState(initial = false)
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

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                Text(text = item.name, modifier = Modifier.padding(8.dp))
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing, state = pullRefreshState, Modifier.align(
                Alignment.TopCenter
            )
        )
    }

    if (isLoadingMore.value) {
        LaunchedEffect(Unit) {
            viewModel.fetchItems()
        }
    }
}
package com.nambv.android_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.nambv.android_mvvm.ui.theme.MyAppTheme
import com.nambv.android_mvvm.ui.view.screens.UsersViewModel
import com.nambv.android_mvvm.ui.view.screens.UsersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    UsersScreen(viewModel = viewModel)
                }
            }
        }
    }
}


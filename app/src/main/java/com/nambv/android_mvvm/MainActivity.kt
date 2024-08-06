package com.nambv.android_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nambv.android_mvvm.ui.navigation.NavGraph
import com.nambv.android_mvvm.ui.theme.MyAppTheme
import com.nambv.android_mvvm.ui.view.screens.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                NavGraph()
            }
        }
    }
}


package com.nambv.android_mvvm.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nambv.android_mvvm.data.model.User
import com.nambv.android_mvvm.ui.view.screens.UserDetailScreen
import com.nambv.android_mvvm.ui.view.screens.UsersScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun NavGraph(starDestination: String = "users") {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = starDestination
    ) {
        composable("users") {
            UsersScreen(navController)
        }
        composable(
            "user_details/{user}",
            arguments = listOf(
                navArgument("user") {
                    type = NavType.ParcelableType(User::class.java)
                }
            )
        ) { backStackEntry ->
            val user = backStackEntry.arguments?.getParcelable<User>("user", User::class.java)
            UserDetailScreen(navController, user = user)
        }
    }
}
package com.example.mychatapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mychatapp.auth.AuthViewModel
import com.example.mychatapp.auth.LoginScreen
import com.example.mychatapp.auth.RegistrationScreen
import com.example.mychatapp.chat.ChatListScreen
import com.example.mychatapp.chat.ChatScreen
import com.example.mychatapp.chat.UserListViewModel
import com.example.mychatapp.profile.ProfileScreen
import com.example.mychatapp.profile.ProfileViewModel
import com.example.mychatapp.settings.SettingsScreen


@Composable
fun AppNavigation(navController: NavHostController,modifier: Modifier){


//    val navController = rememberNavController()
    val authViewModel : AuthViewModel = viewModel()
    val userViewModel : UserListViewModel = viewModel()
    val profileViewModel : ProfileViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routs.LoginScreen){

        composable(Routs.LoginScreen) {
            LoginScreen(navController,authViewModel)
        }

        composable(Routs.SignUpScreen) {
            RegistrationScreen(navController, authViewModel)
        }

        composable(Routs.chatListScreen) {
            ChatListScreen(userViewModel, modifier, navController,authViewModel)
        }

        // 🔹 Individual Chat Screen
        composable(
            route = "${Routs.MessageScreen}/{receiverId}/{receiverName}",
            arguments = listOf(
                navArgument("receiverId") { type = NavType.StringType },
                navArgument("receiverName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val receiverId = backStackEntry.arguments?.getString("receiverId") ?: ""
            val receiverName = backStackEntry.arguments?.getString("receiverName") ?: ""
            val currentUserId = authViewModel.getCurrentUser()?.uid ?: ""


            ChatScreen(
                userId = currentUserId,
                userName = authViewModel.getCurrentUser()?.displayName ?: "You",
                receiverId = receiverId,
                receiverName = receiverName,
                authViewModel = authViewModel,
                navController = navController,
                userListViewModel = userViewModel

            )
        }


        composable(Routs.ProfileScreen) {
            ProfileScreen(profileViewModel,navController)
        }


        composable(Routs.SettingsScreen) {
            SettingsScreen(navController)

        }

    }



}
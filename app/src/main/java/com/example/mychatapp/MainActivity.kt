package com.example.mychatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mychatapp.auth.AuthViewModel
import com.example.mychatapp.auth.RegistrationScreen
import com.example.mychatapp.chat.ChatListScreen
import com.example.mychatapp.chat.UserListViewModel
import com.example.mychatapp.navigation.AppNavigation
//import com.example.mychatapp.navigation.BottomNavigationApp
import com.example.mychatapp.ui.theme.MyChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
        setContent {

            MyChatAppTheme {
                val navController = rememberNavController()
                val viewModel: AuthViewModel = viewModel()
//                val UserviewModel : UserListViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ChatUi(modifier = Modifier.padding(innerPadding))
                    //   RegistrationScreen(navController = navController,  viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                    AppNavigation(navController = navController,modifier = Modifier.padding(innerPadding))


//                    ChatListScreen(UserviewModel, modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}



//        try to put it inside the setContent() function

//        val navController = rememberNavController()
//        val firebaseAuth = FirebaseAuth.getInstance()
//        val currentUser = firebaseAuth.currentUser
//
//        LaunchedEffect(Unit) {
//            if (currentUser != null) {
//                // 👇 user already signed in, go to chat list
//                navController.navigate(Routs.ChatListScreen) {
//                    popUpTo(Routs.LoginScreen) { inclusive = true }
//                }
//            } else {
//                // 👇 no user, show login
//                navController.navigate(Routs.LoginScreen)
//            }
//        }
//
//        AppNavigation(modifier = Modifier.padding(innerPadding))
//    }
//    }
//}



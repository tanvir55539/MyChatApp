//package com.example.mychatapp.navigation
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.mychatapp.R
//
////enum class Destination(val route: String, val icon: Int, val label: String) {
////    Chats(Routs.chatListScreen, R.drawable.botombarchats, "Chats"),
////    Profile(Routs.profileScreen, R.drawable.bottombarprofile, "Profile")
////}
//
//@Composable
//fun BottomNavigationApp() {
//    val navController = rememberNavController()
//    var selectedDestination by rememberSaveable { mutableStateOf(Routs.chatListScreen) }
//
//    Scaffold(
//        bottomBar = {
//            NavigationBar {
//                Destination.values().forEach { destination ->
//                    NavigationBarItem(
//                        selected = selectedDestination == destination.route,
//                        onClick = {
//                            selectedDestination = destination.route
//                            navController.navigate(destination.route) {
//                                launchSingleTop = true
//                                popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
//                                restoreState = true
//                            }
//                        },
//                        icon = {
//                            Icon(
//                                painter = painterResource(id = destination.icon),
//                                contentDescription = destination.label
//                            )
//                        },
//                        label = { Text(destination.label) }
//                    )
//                }
//            }
//        }
//    ) { contentPadding ->
//        AppNavigation(
//            navController = navController,
//            modifier = Modifier.padding(contentPadding)
//        )
//    }
//}

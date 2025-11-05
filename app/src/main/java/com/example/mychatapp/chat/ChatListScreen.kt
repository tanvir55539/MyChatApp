package com.example.mychatapp.chat

import ChatBottomBar
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.example.mychatapp.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mychatapp.auth.AuthViewModel
import com.example.mychatapp.data.model.User
import com.example.mychatapp.navigation.Routs

@Composable
fun ChatListScreen(
    useListviewModel: UserListViewModel = viewModel(),
    modifier: Modifier,
    navController: NavController,
    authViewModel: AuthViewModel


) {

    val users by useListviewModel.userList.collectAsState()
    val currentUserId = authViewModel.getCurrentUser()?.uid ?: ""

    val otherUsers = users.filter { it.uid != currentUserId }

    val messageViewModel: MessageViewModel = viewModel()



    Scaffold (
//        modifier = Modifier.background(Color(0xFF2368D5)),
        topBar = {
            ChatListTopBar()
        },
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF2368D5))
                    .padding(paddingValues)


            ) {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(otherUsers ) { user ->
                        IndividualUser(user,navController,messageViewModel,currentUserId, useListviewModel)

                    }

                }
            }

        },
        bottomBar = {ChatBottomBar(navController)}
    )

}



@Composable
fun IndividualUser(user: User,
                   navController: NavController,
                   viewModel: MessageViewModel,
                   currentUserId : String,
                   userListViewModel: UserListViewModel
) {

//    var lastMessage by remember { mutableStateOf("Loading...")}
    val lastMessages by viewModel.lastMessage.collectAsState()
    val lastMessage = lastMessages[user.uid] ?: "Loading..."

    val photoBitmap = remember(user.photoBase64) {
        userListViewModel.decodeBase64ToBitmap(user.photoBase64)

    }

    LaunchedEffect(user.uid) {
        viewModel.getLastMessage(currentUserId, user.uid)
    }


    Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("${Routs.MessageScreen}/${user.uid}/${Uri.encode(user.name)}")
//                    Log.d("ChatDebugOK", "Navigating to chat with receiverId=$user.uid, receiverName=$user.name")


                }
                .padding(12.dp)
        ) {

        if(photoBitmap != null){
            Image(
                bitmap = photoBitmap.asImageBitmap(),
                contentDescription = "user image",
                modifier = Modifier.size(50.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)

            )
        }else{

            Image(
                painter = painterResource(R.drawable.profileimg),
                contentDescription = "Artist image",
                modifier = Modifier.size(50.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)
//                .background(colorResource(id = R.color.logoColor), CircleShape)
            )

        }


            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text(
                    text = user.name,
                    modifier = Modifier,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = lastMessage,
//                    text = viewModel.message,
                    modifier = Modifier,
                    fontWeight = FontWeight.W200,
                )
            }
        }


}



@Composable
fun ChatListTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2368D5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Chats",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}


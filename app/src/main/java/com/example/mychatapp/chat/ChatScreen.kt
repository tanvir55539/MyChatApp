//package com.example.mychatapp.chat
//
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Send
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.mychatapp.R
//import com.example.mychatapp.auth.AuthViewModel
//import com.example.mychatapp.data.model.Message
//import kotlin.math.log
//
//@Composable
//fun ChatScreen(
//    userId: String,
//    userName: String,
//    receiverId: String,
//    receiverName: String,
//    viewModel: MessageViewModel = viewModel(),
//    authViewModel: AuthViewModel,
//    navController: NavController,
//    userListViewModel: UserListViewModel
//) {
//    var messageText by remember { mutableStateOf("") }
//    val messages by viewModel.message.collectAsState()
//
//    LaunchedEffect(receiverId) {
//        viewModel.listenForMessages(userId, receiverId)
//    }
//
//    Scaffold(
//        topBar = {
//            AppBar(receiverName)
//        },
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .background(Color(0xFF2663CB))
//            ) {
//                // Message list
//                LazyColumn(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        .padding(horizontal = 8.dp, vertical = 4.dp),
//                    reverseLayout = true
//                ) {
//                    val messageList = messages.reversed()
//
//                    itemsIndexed(messageList) { index, msg ->
//
//                        val nextMsgSender = messageList.getOrNull(index - 1)?.senderId
//                        val showAvatar = nextMsgSender != msg.senderId
//
//                        MessageBubble(
//                            message = msg,
//                            isOwnMessage = msg.senderId == userId,
//                            showAvatar = showAvatar,
//                            navController = navController,
//                            userListViewModel = userListViewModel
//
//                        )
//                    }
//                }
//
//
//                // Message input area
//                MessageUi(
//                    message = messageText,
//                    onMessageChange = { messageText = it },
//                    onSendClick = {
//                        if (messageText.isNotBlank()) {
//                            viewModel.sendMessage(userId, receiverId, messageText.trim())
//                            messageText = ""
//                        }
//                    }
//                )
//            }
//        }
//    )
//}
//
//
//@Composable
//fun MessageBubble(
//    message: Message,
//    isOwnMessage: Boolean,
//    showAvatar: Boolean,
//    navController: NavController,
//    userListViewModel: UserListViewModel
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp, vertical = 8.dp), //4.dp
//        horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start,
//        verticalAlignment = Alignment.Bottom
//    ) {
//
//        val userImageBase64 = navController.previousBackStackEntry
//            ?.savedStateHandle
//            ?.get<String>("anotherUser")
//
//        val bitmap = remember(userImageBase64) {
//            userListViewModel.decodeBase64ToBitmap(userImageBase64 ?: "")
//        }
////        Log.d("ImageDebug", "Bitmap decoded successfully: $userImageBase64")
//// Show avatar for received messages on the left
//
//        if (!isOwnMessage) {
//            if (showAvatar) {
//                if (bitmap != null) {
//                    Image(
//                        bitmap = bitmap.asImageBitmap(),
//                        contentDescription = "user image",
//                        modifier = Modifier
//                            .size(30.dp)          // mini size
//                            .clip(CircleShape)
//                            .border(1.dp, Color.Gray, CircleShape)
//                    )
//                } else {
//                    Image(
//                        painter = painterResource(R.drawable.profileimg),
//                        contentDescription = "sender image",
//                        modifier = Modifier
//                            .size(30.dp)
//                            .clip(CircleShape)
//                            .border(1.dp, Color.Gray, CircleShape)
//                    )
//                }
//                Spacer(modifier = Modifier.width(4.dp))
//            }
//        }
//
//
//        // Message bubble
//        Box(
//            modifier = Modifier
//                .background(
//                    color = if (isOwnMessage) Color(0xFF0D47A1) else Color.White,
//                    shape = MaterialTheme.shapes.medium
//                )
//                .padding(horizontal = 12.dp, vertical = 8.dp)
//        ) {
//            Text(
//                text = message.text,
//                color = if (isOwnMessage) Color.White else Color.Black,
//                fontSize = 16.sp
//            )
//        }
//        Spacer(modifier = Modifier.width(4.dp))
//
//
//    }
//}
//
//@Composable
//fun MessageUi(
//    message: String,
//    onMessageChange: (String) -> Unit,
//    onSendClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        OutlinedTextField(
//            modifier = Modifier.weight(1f),
//            value = message,
//            onValueChange = onMessageChange,
//            placeholder = { Text("Type a message...") },
//            singleLine = true
//        )
//
//        IconButton(onClick = onSendClick) {
//            Icon(
//                imageVector = Icons.Default.Send,
//                contentDescription = "Send",
//                tint = Color.White
//            )
//        }
//    }
//}
//
//@Composable
//fun AppBar(userName: String) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF2663CB))
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = userName,
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp,
//            color = Color.White
//        )
//    }
//}




package com.example.mychatapp.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mychatapp.R
import com.example.mychatapp.auth.AuthViewModel
import com.example.mychatapp.data.model.Message

@Composable
fun ChatScreen(
    userId: String,
    userName: String,
    receiverId: String,
    receiverName: String,
    viewModel: MessageViewModel = viewModel(),
    authViewModel: AuthViewModel,
    navController: NavController,
    userListViewModel: UserListViewModel
) {
    var messageText by remember { mutableStateOf("") }
    val messages by viewModel.message.collectAsState()

    LaunchedEffect(receiverId) {
        viewModel.listenForMessages(userId, receiverId)
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
    )

    Scaffold(
        topBar = {
            AppBar(receiverName) {
                navController.popBackStack()
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBackground)
                    .padding(paddingValues)
            ) {
                // Message list
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    reverseLayout = true
                ) {
                    val messageList = messages.reversed()

                    itemsIndexed(messageList) { index, msg ->
                        val nextMsgSender = messageList.getOrNull(index - 1)?.senderId
                        val showAvatar = nextMsgSender != msg.senderId

                        MessageBubble(
                            message = msg,
                            isOwnMessage = msg.senderId == userId,
                            showAvatar = showAvatar,
                            navController = navController,
                            userListViewModel = userListViewModel
                        )
                    }
                }

                // Message input area
                MessageUi(
                    message = messageText,
                    onMessageChange = { messageText = it },
                    onSendClick = {
                        if (messageText.isNotBlank()) {
                            viewModel.sendMessage(userId, receiverId, messageText.trim())
                            messageText = ""
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun MessageBubble(
    message: Message,
    isOwnMessage: Boolean,
    showAvatar: Boolean,
    navController: NavController,
    userListViewModel: UserListViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        val userImageBase64 = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<String>("anotherUser")

        val bitmap = remember(userImageBase64) {
            userListViewModel.decodeBase64ToBitmap(userImageBase64 ?: "")
        }

        if (!isOwnMessage && showAvatar) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "User image",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.profileimg),
                    contentDescription = "Sender image",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
        }

        // Message bubble
        Box(
            modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(
                    color = if (isOwnMessage) Color(0xFF0D47A1) else Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = message.text,
                color = if (isOwnMessage) Color.White else Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun MessageUi(
    message: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(30.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White.copy(alpha = 0.1f)),
            value = message,
            onValueChange = onMessageChange,
            placeholder = { Text("Type a message...", color = Color.White.copy(0.7f)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(6.dp))

        IconButton(onClick = onSendClick) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                tint = Color(0xFF259EB4),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun AppBar(userName: String, onBackClick: () -> Unit

) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = userName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

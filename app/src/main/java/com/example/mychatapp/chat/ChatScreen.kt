package com.example.mychatapp.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    authViewModel: AuthViewModel
) {
    var messageText by remember { mutableStateOf("") }
    val messages by viewModel.message.collectAsState()

    LaunchedEffect(receiverId) {
        viewModel.listenForMessages(userId, receiverId)
    }

    Scaffold(
        topBar = {
            AppBar(receiverName)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFF2663CB))
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
                            showAvatar = showAvatar
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
fun MessageBubble(message: Message, isOwnMessage: Boolean, showAvatar: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Show avatar for received messages on the left
        if (!isOwnMessage) {
            if (showAvatar) {
                Image(
                    painter = painterResource(R.drawable.profileimg),
                    contentDescription = "receiver image",
                    modifier = Modifier
                        .size(24.dp)          // mini size
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        // Message bubble
        Box(
            modifier = Modifier
                .background(
                    color = if (isOwnMessage) Color(0xFF0D47A1) else Color.White,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = message.text,
                color = if (isOwnMessage) Color.White else Color.Black,
                fontSize = 16.sp
            )
        }

        // Show avatar for sent messages on the right
        if (isOwnMessage) {
            if(showAvatar)
            {
                Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(R.drawable.profileimg),
                contentDescription = "sender image",
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
             )
           }
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
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = onMessageChange,
            placeholder = { Text("Type a message...") },
            singleLine = true
        )

        IconButton(onClick = onSendClick) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                tint = Color.White
            )
        }
    }
}

@Composable
fun AppBar(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2663CB))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = userName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

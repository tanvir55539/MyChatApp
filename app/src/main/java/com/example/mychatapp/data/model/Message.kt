package com.example.mychatapp.data.model

data class Message(
    val messageId: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val text: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

//data class Message(
//    val messageId: String,
//    val senderId: String,
//    val receiverId: String,
//    val text: String,
//    val timestamp: Long = System.currentTimeMillis()
//)

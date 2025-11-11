package com.example.mychatapp.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychatapp.data.model.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel(){


    private val firestore = Firebase.firestore

    private val _message = MutableStateFlow<List<Message>>(emptyList())
    val message: StateFlow<List<Message>> = _message

//    private val _lastMessage = MutableStateFlow<Map<String, Message>>(emptyMap())
//    val lastMessage : StateFlow<Map<String, Message>> = _lastMessage

    private val _lastMessage = MutableStateFlow<Map<String, String>>(emptyMap())
    val lastMessage: StateFlow<Map<String, String>> = _lastMessage


//    private val _lastMessage = MutableStateFlow<String>("")
//    val lastMessage : StateFlow<String> = _lastMessage


    private var listenerRegistration: ListenerRegistration? = null

    private fun generateChatId(user1: String, user2: String): String {
        return listOf(user1, user2).sorted().joinToString("_")
    }



    fun sendMessage(senderId: String, receiverId:String, text: String){
        if(text.isBlank()) return

        val chatId = generateChatId(senderId, receiverId)
        Log.d("ChatDebug", "Sending to chatId=$chatId")

        val message = Message(
            senderId = senderId,
            receiverId = receiverId,
            text = text,
            timestamp = System.currentTimeMillis()
        )

        firestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .add(message)
    }


    /**
     * Listen for new messages between two users
     */
    fun listenForMessages(senderId: String, receiverId: String) {
        val chatId = generateChatId(senderId, receiverId)

        // Remove any existing listener first
        listenerRegistration?.remove()

        listenerRegistration = firestore.collection("chats")
//            firestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener

                val newMessages = snapshot.documents.mapNotNull { it.toObject(Message::class.java) }

                viewModelScope.launch {
                    _message.value = newMessages
                }
            }
    }


    fun getLastMessage(senderId: String, receiverId: String){

        val chatId = generateChatId(senderId,receiverId)

        firestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { snapshot ->
                val lastMessage = snapshot.documents.firstOrNull()?.getString("text") ?: "No message yet"
//                onResult(lastMessage ?: "No message yet")

                viewModelScope.launch {
                    _lastMessage.value = _lastMessage.value.toMutableMap().apply {
                        this[receiverId] = lastMessage
                    }
                }

            }
            .addOnFailureListener {
                viewModelScope.launch {
                    _lastMessage.value = _lastMessage.value.toMutableMap().apply {
                        this[receiverId] = "No message yet"
                    }
                }

            }

    }

    // creating the short form of the last message
    fun shortLastMessage(message: String, maxMsgLength: Int = 10): String{

        return if (message.length > maxMsgLength){
           message.take(maxMsgLength)+"..."
        }else{
            message
        }

    }



    /**
     * Stop listening when ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}




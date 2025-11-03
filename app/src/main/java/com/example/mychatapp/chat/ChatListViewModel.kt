package com.example.mychatapp.chat


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mychatapp.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class UserListViewModel : ViewModel(){

    private val firestoreDb = Firebase.firestore

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList : StateFlow<List<User>> = _userList // public read only

    init {
        fetchUsers()
    }

    private fun fetchUsers(){
        firestoreDb.collection("users")
            .get()
            .addOnSuccessListener { result ->
                _userList.value = result.mapNotNull { it.toObject(User::class.java) }
//                Log.d("userlist", "userinfo: ${_userList.value}")

            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error getting users", e)
            }
    }


    fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }





}
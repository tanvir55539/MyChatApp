//package com.example.mychatapp.profile
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.ImageDecoder
//import android.net.Uri
//import android.os.Build
//import android.provider.MediaStore
//import android.util.Base64
//import android.widget.Toast
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.mychatapp.auth.AuthViewModel
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//import com.google.firebase.firestore.firestore
//import com.google.firebase.storage.storage
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import java.io.ByteArrayOutputStream
//
//
//class ProfileViewModel : ViewModel(){
//
//    private val firestore = Firebase.firestore
//    private val storage = Firebase.storage
//
//    private val _currentUSerName = MutableStateFlow<String>("")
//    val currentUserName : StateFlow<String> = _currentUSerName
//
////    private val _profileImageUrl = MutableStateFlow<String?>(null)
////    val profileImageUrl : StateFlow<String?> = _profileImageUrl
//
//    private val _profileImageBase64 = MutableStateFlow<String?>(null)
//    val profileImageBase64: StateFlow<String?> = _profileImageBase64
//
//   private val currentUserId = Firebase.auth.currentUser?.uid
//
//    init {
//        getCurrentUSerData()
//    }
//
//    fun getCurrentUSerData(){
//        val uid = currentUserId ?: return
//        firestore.collection("users")
//            .document(uid)
//            .get()
//            .addOnSuccessListener { result ->
////                val username = result.getString("name") ?: ""
//                _currentUSerName.value = result.getString("name") ?: ""
//                _profileImageBase64.value = result.getString("photoUrl")
//
//            }
//    }
//
//
////    fun uploadProfilePicture(uri: Uri){
////
////        val uid = currentUserId ?: return
////        val storageRef = storage.reference.child("profileImages/$uid.jpg")
////
////        val uploadTask = storageRef.putFile(uri)
////        uploadTask.addOnSuccessListener {
////            storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
////
//////                firestore.collection("users")
//////                    .document(uid)
//////                    .update("photoUrl", downloadUri.toString())
//////                    .addOnSuccessListener {
//////                        _profileImageUrl.value = downloadUri.toString()
//////                    }
////
////                saveProfileImageUrl(downloadUri.toString())
////
////            }
////        }
////            .addOnFailureListener {
////                it.printStackTrace()
//////                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
////            }
////    }
////
////
////    private fun saveProfileImageUrl(url : String){
////
////        val uid = currentUserId ?: return
////        firestore.collection("users")
////            .document(uid)
////            .update("photoUrl", url)
////            .addOnSuccessListener {
////                _profileImageUrl.value = url
////            }
////
////    }
//
//
//    fun uploadProfilePicture(context: Context, uri: Uri) {
//        val uid = currentUserId ?: return
//
//        try {
//            // Convert URI → Bitmap
//            val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
//                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
//            } else {
//                val source = ImageDecoder.createSource(context.contentResolver, uri)
//                ImageDecoder.decodeBitmap(source)
//            }
//
//            // Convert Bitmap → Base64
//            val outputStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
//            val imageBytes = outputStream.toByteArray()
//            val base64String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//
//            // Save Base64 string in Firestore
//            firestore.collection("users")
//                .document(uid)
//                .update("photoBase64", base64String)
//                .addOnSuccessListener {
//                    _profileImageBase64.value = base64String
//                }
//                .addOnFailureListener {
//                    it.printStackTrace()
//                }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}
//

// the upper commented code use the firebase storage but as it is noe free below code use firestore
// is is free but have to store mini size image like 100 to 200kb image to increse the user , as over all free firestore storage is almost 1GB

//package com.example.mychatapp.profile
//
//import android.graphics.Bitmap
//import android.net.Uri
//import android.util.Base64
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//import com.google.firebase.firestore.firestore
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import java.io.ByteArrayOutputStream
//import android.content.Context
//import com.example.mychatapp.auth.AuthViewModel
//
//import android.provider.MediaStore
//import com.google.firebase.auth.FirebaseAuth
//
//
//class ProfileViewModel : ViewModel() {
//
//    private val firestore = Firebase.firestore
////    private val currentUserId = Firebase.auth.currentUser ?. uid
//    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
////    private val auth = Firebase.auth
//
//    private val _currentUserName = MutableStateFlow("")
//    val currentUserName: StateFlow<String> = _currentUserName
//
//    private val _profileImageBase64 = MutableStateFlow<String?>(null)
//    val profileImageBase64: StateFlow<String?> = _profileImageBase64
//
//    init {
//
//        getCurrentUserData()
//    }
//
//    fun getCurrentUserData() {
//        val uid = currentUserId ?: return
//        firestore.collection("users")
//            .document(uid)
//            .get()
//            .addOnSuccessListener { result ->
//                _currentUserName.value = result.getString("name") ?: ""
//                _profileImageBase64.value = result.getString("photoBase64")
//            }
//    }
//
//    fun uploadProfilePicture(context: Context, uri: Uri) {
//        val uid = currentUserId ?: return
//        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
//        val baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos)
//        val byteArray = baos.toByteArray()
//        val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
//
//        viewModelScope.launch {
//            firestore.collection("users")
//                .document(uid)
//                .update("photoBase64", base64String)
//                .addOnSuccessListener {
//                    _profileImageBase64.value = base64String
//                }
//                .addOnFailureListener {
//                    it.printStackTrace()
//                }
//        }
//    }
//}

package com.example.mychatapp.profile

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.ByteArrayOutputStream

class ProfileViewModel : ViewModel() {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _currentUserName = MutableStateFlow("")
    val currentUserName: StateFlow<String> = _currentUserName

    private val _profileImageBase64 = MutableStateFlow<String?>(null)
    val profileImageBase64: StateFlow<String?> = _profileImageBase64

//    init {
//        getCurrentUserData()
//    }

    private fun getUid(): String? = auth.currentUser?.uid

    fun getCurrentUserData() {
        val uid = getUid() ?: return
        firestore.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { result ->
                if (result != null && result.exists()) {
                    _currentUserName.value = result.getString("name") ?: ""
                    _profileImageBase64.value = result.getString("photoBase64")
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun uploadProfilePicture(context: Context, uri: Uri) {
        val uid = getUid() ?: return
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos)
        val byteArray = baos.toByteArray()
        val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        firestore.collection("users")
            .document(uid)
            .update("photoBase64", base64String)
            .addOnSuccessListener {
                _profileImageBase64.value = base64String
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }
}

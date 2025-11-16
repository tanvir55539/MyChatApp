
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

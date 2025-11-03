package com.example.mychatapp.auth

import androidx.lifecycle.ViewModel
import com.example.mychatapp.data.model.userModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class AuthViewModel : ViewModel(){


    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authSate: StateFlow<AuthState> = _authState

      private val auth = Firebase.auth
      private val firestore = Firebase.firestore


    // main signup logic function
    fun signUp(name: String, email:String, password: String,  onResult:(Boolean, String?)-> Unit){
        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){

                    var userId = task.result?.user?.uid
                    val userModel = userModel(name,email, userId!!)
                    firestore.collection("users").document(userId)
                        .set(userModel)
                        .addOnCompleteListener { dbTask ->
                            if(dbTask.isSuccessful){
                                _authState.value = AuthState.Success
                                onResult(true,null)
                            }else{
//                                val message = task.exception ?. message ?: "SignUp Fail"
//                                _authState.value = AuthState.Error(message)
                                onResult(false, "Something Went wrong!!")
                            }

                        }
                }
                else {
                    val message = task.exception ?. message ?: "SignUp Fail"
                    _authState.value = AuthState.Error(message)
                    onResult(false,message)
                }


        }

    }


    fun login(email: String,password: String,onResult: (Boolean, String?) -> Unit){
    _authState.value = AuthState.Loading

     auth.signInWithEmailAndPassword(email,password)
         .addOnCompleteListener { task ->
             if(task.isSuccessful){
                _authState.value = AuthState.Success
                onResult(true,null)
             }
             else{
                 val message = task.exception?. message ?: "Login faild"
                 _authState.value = AuthState.Error(message)
                 onResult(false,message)
             }

         }

    }


    fun loguot(){
        auth.signOut()
        _authState.value = AuthState.Idle
    }




    // it will be used later for directly redirect to chatlist if user is already loged in
    fun getCurrentUser() = auth.currentUser

    private fun mapFirebaseError(e: Exception?): String {
        val msg = e?.message ?: "Unknown error"
        return when {
            msg.contains("email address is badly formatted") -> "Invalid email format"
            msg.contains("password is invalid") -> "Incorrect password"
            msg.contains("no user record") -> "No account found with this email"
            msg.contains("WEAK_PASSWORD") -> "Password should be at least 6 characters"
            else -> msg
        }
    }


}
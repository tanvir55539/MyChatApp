package com.example.mychatapp.data.model


// this model is used to store the user data like
// name, email, uid in the firestore
// we are using this user model data class in AuthViewModel primarily
data class userModel(
    val name: String,
    val email: String,
    val uid: String
)


// while getting the data from firestore i have to make them default =""
// or the type of default data same as the type of data that is strode in firestore

data class User(
    var name: String = "",
    var email: String = "",
    var uid: String = "",
    var photoBase64: String = ""
)

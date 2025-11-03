package com.example.mychatapp.auth

sealed class AuthState{
    object Idle : AuthState()              // No operation yet
    object Loading : AuthState()           // When an API call or process is ongoing
    object Success : AuthState()           // When login/signup succeeds
    data class Error(val message: String) : AuthState() // When something goes wrong
}
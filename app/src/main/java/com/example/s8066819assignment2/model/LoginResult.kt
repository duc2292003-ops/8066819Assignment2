package com.example.s8066819assignment2.model

sealed class LoginResult {
    data class Success(val keypass: String) : LoginResult()
    data class Error(val message: String) : LoginResult()
    object Loading : LoginResult()
}

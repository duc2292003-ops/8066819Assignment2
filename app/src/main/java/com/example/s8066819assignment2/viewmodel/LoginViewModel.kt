package com.example.s8066819assignment2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8066819assignment2.model.LoginRequest
import com.example.s8066819assignment2.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    val loginResult = MutableLiveData<Result<String>>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(username, password))
                loginResult.postValue(Result.success(response.keypass))
            } catch (e: Exception) {
                loginResult.postValue(Result.failure(e))
            }
        }
    }
}

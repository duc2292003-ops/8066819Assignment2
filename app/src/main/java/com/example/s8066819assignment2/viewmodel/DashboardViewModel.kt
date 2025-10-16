package com.example.s8066819assignment2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8066819assignment2.model.Entity
import com.example.s8066819assignment2.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    val entities = MutableLiveData<List<Entity>>()
    val errorMessage = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadEntities(keypass: String) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = apiService.getDashboard(keypass)
                entities.value = response.entities
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
                entities.value = emptyList()
            } finally {
                isLoading.value = false
            }
        }
    }
}

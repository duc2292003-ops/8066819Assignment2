package com.example.s8066819assignment2.network

import com.example.s8066819assignment2.model.*
import retrofit2.http.*

interface ApiService {
    @POST("sydney/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
}
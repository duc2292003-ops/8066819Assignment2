package com.example.s8066819assignment2.model

import java.io.Serializable

data class Entity(
    val name: String,
    val architect: String,
    val location: String,
    val yearCompleted: Int,
    val style: String,
    val height: Int,
    val description: String
) : Serializable
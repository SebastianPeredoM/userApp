package com.example.mdp_prueba.pantallas.usuarios.model.datos

import com.google.gson.annotations.SerializedName

data class User (
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("username")
    val username: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("address")
    val address: Address,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("website")
    val website: String,
    @field:SerializedName("company")
    val company: Company
)
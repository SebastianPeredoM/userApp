package com.example.mdp_prueba.pantallas.usuarios.model.datos

import com.google.gson.annotations.SerializedName

data class Company (
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("catchPhrase")
    val catchPhrase: String,
    @field:SerializedName("bs")
    val bs: String
)
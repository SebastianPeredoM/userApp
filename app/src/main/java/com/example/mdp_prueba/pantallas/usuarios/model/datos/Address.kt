package com.example.mdp_prueba.pantallas.usuarios.model.datos

import com.google.gson.annotations.SerializedName

data class Address (
    @field:SerializedName("street")
    val street: String,
    @field:SerializedName("suite")
    val suite: String,
    @field:SerializedName("city")
    val city: String,
    @field:SerializedName("zipcode")
    val zipcode: String,
    @field:SerializedName("geo")
    val geo: Geo
)
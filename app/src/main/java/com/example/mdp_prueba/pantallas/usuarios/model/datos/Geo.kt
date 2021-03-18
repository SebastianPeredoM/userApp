package com.example.mdp_prueba.pantallas.usuarios.model.datos

import com.google.gson.annotations.SerializedName

data class Geo (
    @field:SerializedName("lat")
    val lat: String,
    @field:SerializedName("lng")
    val lng: String
)
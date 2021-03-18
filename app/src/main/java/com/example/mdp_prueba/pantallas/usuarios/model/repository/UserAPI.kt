package com.example.mdp_prueba.pantallas.usuarios.model.repository

import com.example.mdp_prueba.pantallas.usuarios.model.datos.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {

    @GET("users")
    fun getUsuarios(): Observable<List<User>>
}
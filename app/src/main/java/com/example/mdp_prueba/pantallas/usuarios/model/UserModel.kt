package com.example.mdp_prueba.pantallas.usuarios.model

import com.example.mdp_prueba.pantallas.usuarios.UserMVP
import com.example.mdp_prueba.pantallas.usuarios.model.datos.User
import com.example.mdp_prueba.pantallas.usuarios.model.repository.UserAPI
import com.example.mdp_prueba.root.MyApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserModel(): UserMVP.Model {

    @Inject
    lateinit var userApi: UserAPI

    init {
        MyApp.instance.getComponent().inject(this)
    }

    override fun getUsuarios(): Observable<List<User>> {
        return userApi.getUsuarios()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
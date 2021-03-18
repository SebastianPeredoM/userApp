package com.example.mdp_prueba.pantallas.usuarios

import com.example.mdp_prueba.pantallas.usuarios.model.datos.User
import io.reactivex.Observable

interface UserMVP {
    interface View {
        fun setResultados(users: List<User>)

        fun setSkeleton(enable: Boolean)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun setView(view: UserMVP.View)
        fun getUsuarios()

        fun unsubscribe()
    }

    interface Model {
        fun getUsuarios(): Observable<List<User>>
    }
}
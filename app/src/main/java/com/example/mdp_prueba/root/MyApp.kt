package com.example.mdp_prueba.root

import android.app.Application
import com.example.mdp_prueba.http.ApiModule
import com.example.mdp_prueba.pantallas.usuarios.UserModule

class MyApp: Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .userModule(UserModule())
            .apiModule(ApiModule())
            .build()
    }

    fun getComponent(): ApplicationComponent {
        return component
    }

    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
    }
}
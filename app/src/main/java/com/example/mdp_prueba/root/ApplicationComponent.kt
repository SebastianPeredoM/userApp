package com.example.mdp_prueba.root

import com.example.mdp_prueba.http.ApiModule
import com.example.mdp_prueba.pantallas.usuarios.UserModule
import com.example.mdp_prueba.pantallas.usuarios.model.UserModel
import com.example.mdp_prueba.pantallas.usuarios.view.UserActivity
import com.example.mdp_prueba.root.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, UserModule::class, ApiModule::class])
interface ApplicationComponent {

    fun inject(target: UserActivity)

    fun inject(target: UserModel)
}
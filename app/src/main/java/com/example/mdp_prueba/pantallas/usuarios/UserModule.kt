package com.example.mdp_prueba.pantallas.usuarios

import com.example.mdp_prueba.pantallas.usuarios.model.UserModel
import com.example.mdp_prueba.pantallas.usuarios.presenter.UserPresenter
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideUserPresenter(model: UserMVP.Model): UserMVP.Presenter {
        return UserPresenter(model)
    }

    @Provides
    fun provideUserModel(): UserMVP.Model {
        return UserModel()
    }
}
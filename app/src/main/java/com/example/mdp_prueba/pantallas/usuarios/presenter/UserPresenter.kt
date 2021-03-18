package com.example.mdp_prueba.pantallas.usuarios.presenter

import android.util.Log
import com.example.mdp_prueba.pantallas.usuarios.UserMVP
import io.reactivex.disposables.CompositeDisposable

class UserPresenter(private var model: UserMVP.Model): UserMVP.Presenter {

    private lateinit var view: UserMVP.View
    private val TAG = this::class.java.name

    private val compositeDisposable = CompositeDisposable()

    override fun setView(view: UserMVP.View) {
        this.view = view
    }

    override fun getUsuarios() {
        view.setSkeleton(true)
        val disposable = model.getUsuarios().subscribe(
            {
                if (it.count() == 0) {
                    view.showMessage("No hay usuarios")
                } else {
                    view.setResultados(it)
                }
            },
            {
                it.printStackTrace()
                view.showMessage(it.message ?: "Ocurrió un error")
            },
            {
                view.setSkeleton(false)
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun unsubscribe() {
        if (!compositeDisposable.isDisposed){
            try {
                compositeDisposable.dispose()
            } catch (e:Exception) {
                Log.e(TAG,"Error en destroy")
            }
        }
    }
}
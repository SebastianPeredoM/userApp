package com.example.mdp_prueba.pantallas.usuarios.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.mdp_prueba.R
import com.example.mdp_prueba.helper.showSnackbar
import com.example.mdp_prueba.pantallas.usuarios.UserMVP
import com.example.mdp_prueba.pantallas.usuarios.model.datos.User
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.mdp_prueba.root.MyApp
import com.google.gson.Gson
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserMVP.View, UserAdapter.OnCardListener {

    @Inject
    lateinit var presenter: UserMVP.Presenter

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.rcv_usuarios) lateinit var rcvUsers: RecyclerView
    @BindView(R.id.ly_user_activity) lateinit var lyMain: ConstraintLayout

    lateinit var mAdapter: UserAdapter
    private var skeletonScreen : RecyclerViewSkeletonScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
        (application as MyApp).getComponent().inject(this)
        setSupportActionBar(toolbar)

        mAdapter = UserAdapter(this)
        rcvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = mAdapter
        }

        presenter.setView(this)
        presenter.getUsuarios()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.unsubscribe()
    }

    override fun setResultados(users: List<User>) {
        mAdapter.setLista(users)
    }

    override fun setSkeleton(enable: Boolean) {
        if (enable) {
            skeletonScreen = Skeleton.bind(rcvUsers)
                .adapter(mAdapter)
                .shimmer(true)
                .angle(20)
                .color(R.color.color_shimmer_skeleton)
                .duration(1200)
                .frozen(false)
                .count(10)
                .load(R.layout.item_skeleton_card_usuario)
                .show()
        } else {
            skeletonScreen?.hide()
        }
    }

    override fun showMessage(message: String) {
        lyMain.showSnackbar(message)
    }

    override fun goToDetail(user: User) {
        val intent = Intent(this, DetalleUserActivity::class.java)
        intent.putExtra(DetalleUserActivity.ARG_JSON_USER, Gson().toJson(user))
        startActivity(intent)
    }
}
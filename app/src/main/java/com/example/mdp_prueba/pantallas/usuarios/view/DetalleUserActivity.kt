package com.example.mdp_prueba.pantallas.usuarios.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.mdp_prueba.R
import com.example.mdp_prueba.helper.setImageCircular
import com.example.mdp_prueba.pantallas.usuarios.model.datos.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetalleUserActivity : AppCompatActivity(), OnMapReadyCallback {

    @BindView(R.id.fab_atras) lateinit var fabAtras: FloatingActionButton
    @BindView(R.id.img_user_detalle) lateinit var imgUser: ImageView
    @BindView(R.id.txt_titulo_detalle) lateinit var txtTitulo: TextView
    @BindView(R.id.txt_direccion_detalle) lateinit var txtDireccion: TextView
    @BindView(R.id.txt_telefono_detalle) lateinit var txtTelefono: TextView
    @BindView(R.id.txt_web_detalle) lateinit var txtWeb: TextView
    @BindView(R.id.txt_email_detalle) lateinit var txtEmail: TextView
    @BindView(R.id.txt_empresa_detalle) lateinit var txtEmpresa: TextView
    @BindView(R.id.txt_eslogan_detalle) lateinit var txtEslogan: TextView
    @BindView(R.id.txt_bs_detalle) lateinit var txtBs: TextView

    lateinit var map: GoogleMap
    lateinit var latLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_user)

        ButterKnife.bind(this)

        intent.extras?.apply {
            val jsonUser = getString(ARG_JSON_USER)
            setDatos(Gson().fromJson(jsonUser, User::class.java))
        }
    }

    private fun setDatos(user: User) {
        imgUser.setImageCircular(R.mipmap.nouser, "https://www.grandespymes.com.ar/wp-content/uploads/2020/11/Fotolia_23533273_Subscription_L.jpg")
        txtTitulo.text = "${user.name} (${user.username})"
        txtDireccion.text = "${user.address.street}, ${user.address.city}"
        txtTelefono.text = user.phone
        txtWeb.text = "Página web: ${user.website}"
        txtEmail.text = "Correo: ${user.email}"
        txtEmpresa.text = user.company.name
        txtEslogan.text = user.company.catchPhrase
        txtBs.text = user.company.bs

        latLng = LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble())
        createMap()
    }

    private fun createMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @OnClick(R.id.fab_atras)
    fun fabAtras_click() {
        finish()
    }

    companion object {
        val ARG_JSON_USER = this::class.java.name+"/json_user"
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            map = it
            createMarker()
        }
    }

    private fun createMarker() {
        val marker = MarkerOptions().position(latLng).title("Ubicación")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, 30f),
            4000,
            null
        )
    }
}
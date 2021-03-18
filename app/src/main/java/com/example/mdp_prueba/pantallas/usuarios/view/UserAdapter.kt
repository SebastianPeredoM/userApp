package com.example.mdp_prueba.pantallas.usuarios.view

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.mdp_prueba.R
import com.example.mdp_prueba.helper.inflate
import com.example.mdp_prueba.pantallas.usuarios.model.datos.User

class UserAdapter(val listener: OnCardListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var lista: MutableList<User> = mutableListOf()

    override fun getItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolder(parent.inflate(R.layout.item_card_usuario))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        lista[position].let {
            (holder as ActividadViewHolder).bindView(it, listener, position, itemCount)
        }
    }
    override fun getItemCount(): Int = lista.count()

    fun setLista(newList: List<User>) {
        lista.clear()
        lista.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), ActividadViewHolder {

        @BindView(R.id.item_card_usuario) lateinit var card: CardView
        @BindView(R.id.img_user) lateinit var imgUser: ImageView
        @BindView(R.id.txt_titulo) lateinit var txtTitulo: TextView
        @BindView(R.id.txt_direccion) lateinit var txtDireccion: TextView
        @BindView(R.id.txt_telefono) lateinit var txtTelefono: TextView

        override fun bindView(
            user : User,
            listener : OnCardListener,
            position : Int,
            listCount : Int
        ) {
            ButterKnife.bind(this, itemView)

            txtTitulo.text = "${user.name} (${user.username})"
            txtDireccion.text = "${user.address.street}, ${user.address.city}"
            txtTelefono.text = user.phone

            card.setOnClickListener {
                listener.goToDetail(user)
            }
        }
    }

    interface OnCardListener {
        fun goToDetail(user: User)
    }

    interface ActividadViewHolder {
        fun bindView(
            user : User,
            listener : OnCardListener,
            position : Int,
            listCount : Int
        )
    }
}
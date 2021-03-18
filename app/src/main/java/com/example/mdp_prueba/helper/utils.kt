package com.example.mdp_prueba.helper

import android.content.res.Resources
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.mdp_prueba.R
import com.example.mdp_prueba.helper.picasso.CircleTransform
import com.example.mdp_prueba.root.MyApp
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG, margin: Int = 20) {
    val snackbar = Snackbar.make(this, message, duration)
    val view = snackbar.view
    val textview = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textview.setTextColor(ContextCompat.getColor(MyApp.instance, R.color.white))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        view.elevation = 120.0f
    }
    val fondo = ContextCompat.getDrawable(MyApp.instance, R.drawable.snackbar_ripple)
    view.background = (fondo)
    val lyMargen : ViewGroup.MarginLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    val dpsMargen = margin.dp
    lyMargen.setMargins(dpsMargen, 0, dpsMargen, dpsMargen)
    snackbar.show()
}

fun ViewGroup.inflate(layoutRes : Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.setImageCircular(placeHolder: Int, url: String?, drawable: Int? = null) {
    if (url == null && drawable != null) {
        Picasso.get()
            .load(drawable)
            .placeholder(placeHolder)
            .transform(CircleTransform())
            .centerCrop()
            .fit()
            .into(this)
    } else {
        Picasso.get()
            .load(url)
            .placeholder(placeHolder)
            .transform(CircleTransform())
            .centerCrop()
            .fit()
            .into(this)
    }
}
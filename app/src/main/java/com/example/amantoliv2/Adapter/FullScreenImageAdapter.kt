package com.example.amantoliv2.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amantoliv2.FullScreenActivity
import com.example.amantoliv2.R
import com.example.amantoliv2.Utils.Imagen
import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.list_item.view.*

class FullScreenImageAdapter(private var lista:ArrayList<Imagen>,
                             private var contexto: Context): RecyclerView.Adapter<FullScreenImageAdapter.verHolder>()
{
    class verHolder(var vista: View, var contexto: Context):
        RecyclerView.ViewHolder(vista){
        val ivFullScreen = vista.findViewById<ImageView>(R.id.ivFullScreen)

        fun bind(imgfs: Imagen){
            ivFullScreen.setImageResource(imgfs.imagen)

            ivFullScreen.setOnClickListener {
                contexto.startActivity(
                    Intent(contexto, FullScreenActivity::class.java).putExtra("imgfs", imgfs)
                )//End contexto.startActivity

            }//End cimag.setOnClickListener

        }//End fun bind

    }//End RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): verHolder {
        return verHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),contexto)
    }

    override fun onBindViewHolder(holder: verHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}
package com.example.amantoliv2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager


class MyOrdersHistory : AppCompatActivity() {//Fin class MyOrdersHistory

    lateinit var expandableLayout: LinearLayout
    lateinit var expandBtn: Button
    lateinit var cardView: CardView
    lateinit var tvTrackOrder: TextView
    lateinit var btnTrackMyOrder: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders_history)

        val tvTrackOrder = findViewById<TextView>(R.id.tvTrackOrder)

        tvTrackOrder.setOnClickListener{
            val view = View.inflate(this@MyOrdersHistory, R.layout.dialog_track_order, null)

            val builder = AlertDialog.Builder(this@MyOrdersHistory)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            //val btnTrackMyOrder = findViewById<AppCompatButton>(R.id.btnTrackMyOrder)
            /*btnTrackMyOrder.setOnClickListener {
                //startActivity(intent)
                *//*val intent = Intent(Intent.ACTION_VIEW)
                try {
                    intent.data = Uri.parse("https://tracking.99minutos.com/search")
                    startActivity(intent)
                } catch (exception: ActivityNotFoundException) {
                    //Toast.makeText(getContext(), "Error text", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "¡Ups! Ha ocurrido un error. Vuelve a intentarlo.", Toast.LENGTH_SHORT).show()
                }*//*

                val uri = Uri.parse("http://www.google.com") // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)

            }*/

        }



        var tvExpandOrd = findViewById<TextView>(R.id.tvExpandOrders)
        var expandableLayoutOrd = findViewById<RelativeLayout>(R.id.expandableLayoutOrders)
        var cvOrd = findViewById<CardView>(R.id.cardViewOrders)

        var expandableLayoutDevol = findViewById<RelativeLayout>(R.id.expandableLayoutDevol)
        var tvExpandDevol = findViewById<TextView>(R.id.tvExpandDevol)
        var cvDevol = findViewById<CardView>(R.id.cardViewDevol)

        tvExpandOrd.setOnClickListener {
            if(expandableLayoutOrd.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cvOrd, AutoTransition())
                expandableLayoutOrd.visibility = View.GONE
                tvExpandOrd.text = "Pedidos"
            }else{
                TransitionManager.beginDelayedTransition(cvOrd, AutoTransition())
                expandableLayoutOrd.visibility = View.VISIBLE
                tvExpandOrd.text = "Ocultar"
            }//Fin else
        }

        tvExpandDevol.setOnClickListener {
            if(expandableLayoutDevol.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cvDevol, AutoTransition())
                expandableLayoutDevol.visibility = View.GONE
                tvExpandDevol.text = "Devoluciones"
            }else{
                TransitionManager.beginDelayedTransition(cvDevol, AutoTransition())
                expandableLayoutDevol.visibility = View.VISIBLE
                tvExpandDevol.text = "Ocultar"
            }//Fin else
        }

    }//Fin onCreate

    fun openLink(view: View) {
        val uri = Uri.parse("https://tracking.99minutos.com/search")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }//Fin función openLink (rastreo)
}
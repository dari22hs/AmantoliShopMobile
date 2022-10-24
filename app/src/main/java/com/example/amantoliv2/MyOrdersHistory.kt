package com.example.amantoliv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.amantoliv2.Adapter.CardAdapter

class MyOrdersHistory : AppCompatActivity() {

    lateinit var expandableLayout: LinearLayout
    lateinit var expandBtn: Button
    lateinit var cardView: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders_history)

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
            }
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
            }
        }
    }
}
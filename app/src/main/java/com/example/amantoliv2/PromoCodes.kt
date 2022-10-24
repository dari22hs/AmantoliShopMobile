package com.example.amantoliv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.airbnb.lottie.LottieAnimationView
import com.example.amantoliv2.Adapter.CodesAdapter
import com.example.amantoliv2.Model.Codes
import kotlin.math.exp

class PromoCodes : AppCompatActivity() {

    lateinit var animationView: LottieAnimationView
    val codeList = ArrayList<Codes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo_codes)

        var ltDiscount = findViewById<LottieAnimationView>(R.id.animationViewDiscount)
        var tvExpand = findViewById<TextView>(R.id.tvExpand)
        var expandableLayout = findViewById<RelativeLayout>(R.id.expandableLayout)
        var cv = findViewById<CardView>(R.id.cardView)

        tvExpand.setOnClickListener {
            if(expandableLayout.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cv, AutoTransition())
                expandableLayout.visibility = View.GONE
                tvExpand.text = "Mis códigos"
            }else{
                TransitionManager.beginDelayedTransition(cv, AutoTransition())
                expandableLayout.visibility = View.VISIBLE
                tvExpand.text = "Ocultar"
            }
        }

        /*tvExpand.setOnClickListener {
            if(expandableLayout.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(cv, AutoTransition())
                expandableLayout.visibility = View.VISIBLE
                tvExpand.text = "Ocultar"
            }else{
                TransitionManager.beginDelayedTransition(cv, AutoTransition())
                expandableLayout.visibility = View.GONE
                tvExpand.text = "Mis códigos"
            }
        }*/

        //initData()
        //setRecyclerView()

    }

    private fun setRecyclerView() {
        val codesAdapter = CodesAdapter(codeList)

    }

    private fun initData() {
        TODO("Not yet implemented")
    }
}
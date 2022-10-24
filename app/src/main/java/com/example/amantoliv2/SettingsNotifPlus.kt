package com.example.amantoliv2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import com.airbnb.lottie.LottieAnimationView

class SettingsNotifPlus : AppCompatActivity() {

    lateinit var animationView: LottieAnimationView
    var isChecked = true
    var isBoxChecked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_notif_plus)

        var checkAnim = findViewById<LottieAnimationView>(R.id.animationViewAppMode)
        var chkb1 = findViewById<CheckBox>(R.id.chkb1)


        checkAnim.setOnClickListener{
            if (isChecked){
                checkAnim.speed = -1f
                checkAnim.playAnimation()
                isChecked = false
            }else{
                checkAnim.speed = -1f
                checkAnim.playAnimation()
                isChecked = false
                checkAnim.pauseAnimation()
            }//Fin else
        }//Fin setOnClickListener

    }//Fin fun onCreate
}//Fin class SettingsNotifPlus
package com.example.amantoliv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.amantoliv2.Utils.Imagen

class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val ivFullScreen = findViewById<ImageView>(R.id.ivFullScreen)
        val imagenResult = intent.getSerializableExtra("imgfs") as Imagen

        ivFullScreen.setImageResource(imagenResult.imagen)

    }//End onCreate
}//End class FullScreenActivity
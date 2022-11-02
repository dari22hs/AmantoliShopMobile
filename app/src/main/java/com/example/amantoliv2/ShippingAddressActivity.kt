package com.example.amantoliv2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShippingAddressActivity : AppCompatActivity() {

    lateinit var addAddress_ShippingPage: FloatingActionButton
    lateinit var btnCloseAddressDialog: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)

        addAddress_ShippingPage = findViewById(R.id.addAddress_ShippingPage)
        //btnCloseAddressDialog = view(R.layout.dialog_add_address)

        addAddress_ShippingPage.setOnClickListener{
            val view = View.inflate(this@ShippingAddressActivity, R.layout.dialog_add_address, null)

            val builder = AlertDialog.Builder(this@ShippingAddressActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

    }//Fin onCreate
}//Fin class ShippingAddressActivity
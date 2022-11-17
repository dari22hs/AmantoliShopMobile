package com.example.amantoliv2.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.amantoliv2.R

class SubSellerFragment : Fragment() {

    lateinit var btnSubscribeSellerBasic: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sub_seller, container, false)

        btnSubscribeSellerBasic = view.findViewById(R.id.btnSubscribeSellerBasic)


        btnSubscribeSellerBasic.setOnClickListener {
            val view = View.inflate(activity, R.layout.dialog_basic_sub, null)

            var btnProceedToPayment: AppCompatButton = view.findViewById(R.id.btnProceedToPayment)

            val builder = activity?.let { it1 -> AlertDialog.Builder(it1) }
            if (builder != null) {
                builder.setView(view)
            }

            val dialog = builder?.create()
            if (dialog != null) {
                dialog.show()
            }
            if (dialog != null) {
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }

            btnProceedToPayment.setOnClickListener {
                if (dialog != null) {
                    openLink(view)
                    dialog.dismiss()
                }
            }//End btnCancelLogout.setOnClickListener
        }

        return view

    }//Env View

    fun openLink(view: View) {
        val uri = Uri.parse("https://www.paypal.com/mx/home")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

    }//Fin función openLink (PayPal)

}//Fin class SubBSellerFragment
package com.example.amantoliv2.Utils

import android.app.AlertDialog
import android.view.WindowManager
import com.example.amantoliv2.R
import com.example.amantoliv2.VisualSearchActivity

class LoadingDialog (val mActivity: VisualSearchActivity) {

    private lateinit var isDialog: AlertDialog

    fun startLoading(){

        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()

    }//End fun startLoading

    fun isDismiss(){
        isDialog.dismiss()
    }//End fun isDismiss

}//End class LoadingDialog
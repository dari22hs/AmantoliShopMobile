package com.example.amantoliv2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amantoliv2.Adapter.ProductAdapter
import com.example.amantoliv2.Model.Product
import com.example.amantoliv2.Utils.DefaultCard.GetDefCard
//import com.example.amantoliv2.Utils.Extensions.cardXXGen
import com.example.amantoliv2.Utils.Extensions.toast
import com.example.amantoliv2.db.CartViewModel
import com.example.amantoliv2.db.ProductEntity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ProductReviewActivity : AppCompatActivity() {

    lateinit var productImage_ProductReviewPage: ImageView
    lateinit var btnPostReview: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_review)

        productImage_ProductReviewPage = findViewById(R.id.productImage_ProductReviewPage)

        btnPostReview = findViewById(R.id.btnPostReview)

        btnPostReview.setOnClickListener{
            val view = View.inflate(this@ProductReviewActivity, R.layout.dialog_add_review, null)

            val builder = AlertDialog.Builder(this@ProductReviewActivity)
            builder.setView(view)

            //val inflatedView: View = layoutInflater.inflate(R.layout.dialog_add_address, null)
            //val btnCloseAddressDialog = inflatedView.findViewById<AppCompatButton>(R.id.btnCloseAddressDialog)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            //val btnCloseAddressDialog: AppCompatButton = findViewById(R.id.btnCloseAddressDialog)
            /*btnCloseAddressDialog.setOnClickListener {
                dialog.dismiss()
            }*/

        }

    }//Fin onCreate

}//Fin class ProductReviewActivity
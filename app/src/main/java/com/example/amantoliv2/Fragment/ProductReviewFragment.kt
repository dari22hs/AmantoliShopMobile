package com.example.amantoliv2.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.amantoliv2.R
import com.example.amantoliv2.Adapter.CartAdapter
import com.example.amantoliv2.Adapter.CartItemClickAdapter
import com.example.amantoliv2.Adapter.CategoryAdapter
import com.example.amantoliv2.Adapter.CoverProductAdapter
import com.example.amantoliv2.Model.Category
import com.example.amantoliv2.Model.Product
import com.example.amantoliv2.db.CartViewModel
import com.example.amantoliv2.db.ProductEntity

class ProductReviewFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_review, container, false)


    }//Fin View

}//Fin class ProductReview
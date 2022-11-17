package com.example.amantoliv2.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.amantoliv2.Adapter.CartAdapter
import com.example.amantoliv2.R
import com.example.amantoliv2.db.CartViewModel
import com.example.amantoliv2.db.ProductEntity

import androidx.lifecycle.Observer
import com.example.amantoliv2.Adapter.CartItemClickAdapter
import com.example.amantoliv2.Utils.Extensions.toast
import com.google.android.material.bottomsheet.BottomSheetDialog


class FavFragment : Fragment() {

    //lateinit var cartRecViewFavs: RecyclerView
    //lateinit var cartAdapter: CartAdapter
    lateinit var animationView: LottieAnimationView
    //lateinit var totalPriceBagFrag: TextView
    //lateinit var Item: ArrayList<ProductEntity>
    //var sum:Int = 0

    //private lateinit var cartViewModel: CartViewModel
    //lateinit var productAddToFav_singleProduct: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_fav, container, false)

        val viewProductFav = inflater.inflate(R.layout.single_product, container, false)

        //cartRecViewFavs = view.findViewById(R.id.cartRecViewFavs)
        animationView = view.findViewById(R.id.animationFavsFragment)
        //totalPriceBagFrag = view.findViewById(R.id.totalPriceBagFrag)
        //val bottomCartLayout: LinearLayout = view.findViewById(R.id.bottomCartLayout)
        val emptyFavsLayout: LinearLayout = view.findViewById(R.id.emptyFavsLayout)
        val tvMyFavsFragment:TextView = view.findViewById(R.id.tvMyFavsFragment)

        val llFav1: LinearLayout = view.findViewById(R.id.llFav1)
        val btnRemoveFromFavs: AppCompatButton = view.findViewById(R.id.btnRemoveFromFavs)

        btnRemoveFromFavs.setOnClickListener {
            animationView.playAnimation()
            animationView.loop(true)
            //bottomCartLayout.visibility = View.GONE
            tvMyFavsFragment.visibility = View.GONE
            llFav1.visibility = View.GONE
            emptyFavsLayout.visibility = View.VISIBLE
        }

        //val productAddToFav_singleProduct:ImageView = viewProductFav.findViewById(R.id.productAddToFav_singleProduct)

        /*productAddToFav_singleProduct.setOnClickListener {

            Toast.makeText(context,"Agregado a favoritos",Toast.LENGTH_SHORT).show()

        }//Fin addToFav*/








        return view

    }//End View

}//Fin class FavFragment
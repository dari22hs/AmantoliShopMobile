package com.example.amantoliv2.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.amantoliv2.Adapter.CartAdapter
import com.example.amantoliv2.Adapter.CartItemClickAdapter

import com.example.amantoliv2.R

import com.example.amantoliv2.db.CartViewModel
import com.example.amantoliv2.db.ProductEntity

class BagFragment : Fragment(), CartItemClickAdapter {

    lateinit var cartRecView:RecyclerView
    lateinit var cartAdapter: CartAdapter
    lateinit var checkOut_BagPage: AppCompatButton
    lateinit var animationView: LottieAnimationView
    lateinit var totalPriceBagFrag:TextView
    lateinit var Item: ArrayList<ProductEntity>
     var sum:Int = 0

    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bag, container, false)

        cartRecView = view.findViewById(R.id.cartRecView)
        animationView = view.findViewById(R.id.animationViewCartPage)
        totalPriceBagFrag = view.findViewById(R.id.totalPriceBagFrag)
        checkOut_BagPage = view.findViewById(R.id.checkOut_BagPage)

        val bottomCartLayout:LinearLayout = view.findViewById(R.id.bottomCartLayout)
        val emptyBagMsgLayout:LinearLayout = view.findViewById(R.id.emptyBagMsgLayout)
        val MybagText:TextView = view.findViewById(R.id.MybagText)
        Item = arrayListOf()


        animationView.playAnimation()
        animationView.loop(true)
        bottomCartLayout.visibility = View.GONE
        MybagText.visibility = View.GONE
        emptyBagMsgLayout.visibility = View.VISIBLE

        cartRecView.layoutManager = LinearLayoutManager(context)
        cartAdapter = CartAdapter(activity as Context, this )
        cartRecView.adapter = cartAdapter

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)

        cartViewModel.allproducts.observe(viewLifecycleOwner, Observer {List ->
            List?.let {
                cartAdapter.updateList(it)
                Item.clear()
                sum = 0
                Item.addAll(it)
            }

            if (List.size == 0){
                animationView.playAnimation()
                animationView.loop(true)
                bottomCartLayout.visibility = View.GONE
                MybagText.visibility = View.GONE
                emptyBagMsgLayout.visibility = View.VISIBLE

            }
            else{
                emptyBagMsgLayout.visibility = View.GONE
                bottomCartLayout.visibility = View.VISIBLE
                MybagText.visibility = View.VISIBLE
                animationView.pauseAnimation()//Cambiar arriba ? CHECAR ****************
            }

            Item.forEach {
                sum += it.price
            }
            totalPriceBagFrag.text = "$" + sum
        })

        checkOut_BagPage.setOnClickListener {
            val view = View.inflate(activity, R.layout.dialog_payment_order, null)

            var btnProceedToPayment: AppCompatButton = view.findViewById(R.id.btnProceedToPayment)
            var btnCloseAddressDialog: AppCompatButton = view.findViewById(R.id.btnCloseAddressDialog)

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

            btnCloseAddressDialog.setOnClickListener {
                dialog?.dismiss()
            }

        }

        return view
    }

    fun openLink(view: View) {
        val uri = Uri.parse("https://www.paypal.com/mx/home")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

    }//Fin función openLink (PayPal)

    override fun onItemDeleteClick(product: ProductEntity) {
        cartViewModel.deleteCart(product)
        Toast.makeText(context,"Eliminado de la bolsa",Toast.LENGTH_SHORT).show()
    }

    override fun onItemUpdateClick(product: ProductEntity) {
        cartViewModel.updateCart(product)
    }


}//Fin class BagFragment
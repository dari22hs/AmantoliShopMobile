package com.example.amantoliv2.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amantoliv2.Adapter.CategoryAdapter
import com.example.amantoliv2.Adapter.CoverProductAdapter
import com.example.amantoliv2.CategoryPottery

import com.example.amantoliv2.Model.Category
import com.example.amantoliv2.Model.Product

import com.example.amantoliv2.R
import com.example.amantoliv2.SettingsActivity
import com.example.amantoliv2.databinding.FragmentHomeBinding
import com.example.amantoliv2.databinding.FragmentShopBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class ShopFragment : Fragment() {

    lateinit var cateList:ArrayList<Category>
    lateinit var coverProduct:ArrayList<Product>

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var coverProductAdapter: CoverProductAdapter

    lateinit var cvCategoryPottery: CardView
    lateinit var ivPottery: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val coverRecView_shopFrag : RecyclerView = view.findViewById(R.id.coverRecView_shopFrag)
        val categoriesRecView : RelativeLayout = view.findViewById(R.id.categoriesRecView)
        val cvCategoryPottery: CardView = view.findViewById(R.id.cvCategoryPottery)
        //val ivPottery: ImageView = view.findViewById(R.id.ivPottery)
        val ivPottery = view.findViewById<ImageView>(R.id.ivPottery)

        /*val bind = FragmentShopBinding.inflate(layoutInflater)

        bind.cvCategoryPottery.setOnClickListener{
            val intent = Intent (this@ShopFragment.requireContext(), CategoryPottery::class.java)
            startActivity(intent)
        }

        val bind2 = FragmentHomeBinding.inflate(layoutInflater)

        bind.ivPottery.setOnClickListener{
            val intent = Intent (this@ShopFragment.requireContext(), CategoryPottery::class.java)
            startActivity(intent)
        }*/

        ivPottery.setOnClickListener {
            val intent = Intent(context, CategoryPottery::class.java)
            startActivity(intent)
        }

        cateList = arrayListOf()
        coverProduct = arrayListOf()

        setCoverData()
        setCategoryData()

        coverRecView_shopFrag.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        coverRecView_shopFrag.setHasFixedSize(true)
        coverProductAdapter = CoverProductAdapter(activity as Context, coverProduct )
        coverRecView_shopFrag.adapter = coverProductAdapter

        //categoriesRecView.layoutManager = GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
        //categoriesRecView.setHasFixedSize(true)
        categoryAdapter = CategoryAdapter(activity as Context, cateList )
        //categoriesRecView.adapter = categoryAdapter

        return view
    }

    private fun setCategoryData() {
        cateList.add(Category("Alfarería","https://images.unsplash.com/photo-1609881582722-4a8ab7cd54d8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80"))
        cateList.add(Category("Ropa","https://images.unsplash.com/photo-1632754724733-a220cd51b7d2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465&q=80"))
        cateList.add(Category("Joyería","https://images.unsplash.com/photo-1543294001-f7cd5d7fb516?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"))
        cateList.add(Category("Bolsas","https://images.unsplash.com/photo-1628149455678-16f37bc392f4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=436&q=80"))
        cateList.add(Category("Pinturas","https://images.unsplash.com/photo-1579783900882-c0d3dad7b119?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=408&q=80"))
        cateList.add(Category("Esculturas","https://images.unsplash.com/photo-1622976187016-473a59974d23?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=386&q=80"))
        cateList.add(Category("Juguetes","https://images.unsplash.com/photo-1625651417672-c9aa0a62f269?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80"))
        cateList.add(Category("Varios","https://images.unsplash.com/photo-1596399761617-1a007364e98b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"))
    }

    fun getJsonData(context: Context, fileName: String): String? {

        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun setCoverData() {

        val jsonFileString = context?.let {

            getJsonData(it, "CoverProducts.json")
        }
        val gson = Gson()

        val listCoverType = object : TypeToken<List<Product>>() {}.type

        var coverD: List<Product> = gson.fromJson(jsonFileString, listCoverType)

        coverD.forEachIndexed { idx, person ->
            coverProduct.add(person)
        }

    }

    fun openCategory(){

    }

}



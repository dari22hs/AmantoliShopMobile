package com.example.amantoliv2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amantoliv2.Adapter.ProductAdapterAr
import com.example.amantoliv2.R
import com.example.amantoliv2.products

class ListFragment : Fragment() {//End class ListFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productList = view.findViewById<RecyclerView>(R.id.product_list).apply {
            layoutManager = LinearLayoutManager(activity)

            adapter = ProductAdapterAr{
                findNavController().navigate(ListFragmentDirections.actionHomeToDetail())
            }
            setHasFixedSize(true)
        }
        (productList.adapter as ProductAdapterAr).submitList(products)
    }
}
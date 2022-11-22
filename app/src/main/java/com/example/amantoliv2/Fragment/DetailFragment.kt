package com.example.amantoliv2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.amantoliv2.R
import com.example.amantoliv2.databinding.FragmentDetailBinding
import com.example.amantoliv2.Model.ProductAr
import com.example.amantoliv2.ProductDetailsActivity
import com.example.amantoliv2.products


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }//End View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        var product: ProductAr? = null

        arguments?.let { it ->
            val args = DetailFragmentArgs.fromBundle(it)
            product = products.find { args.id == it.id }
        }

        product?.let {
            with(it){
                binding.productName.text = name
                binding.productPrice.text = price
                binding.productFullDescription.text = longDescription
                binding.productImage.setImageResource(imageId)

                binding.virtual.setOnClickListener {
                    val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
                    val intentUri =
                        Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                            .appendQueryParameter("file", modelURL)
                            .appendQueryParameter("mode", "ar_only")
                            .appendQueryParameter("resizable", "false")
                            .appendQueryParameter("title", "$name - $$price")
                            .build()
                    sceneViewerIntent.data = intentUri
                    //sceneViewerIntent.setPackage("com.google.ar.core")
                    sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
                    startActivity(sceneViewerIntent)
                }

                binding.addToCart.setOnClickListener {
                    Toast.makeText(context, "Agregado a la bolsa", Toast.LENGTH_SHORT).show()
                }

                binding.buyNow.setOnClickListener {
                    //Toast.makeText(context, "Regresando", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, ProductDetailsActivity::class.java)
                    startActivity(intent)

                }

            }
        }

    }//End override fun onViewCreated

}//End class DetailFragment
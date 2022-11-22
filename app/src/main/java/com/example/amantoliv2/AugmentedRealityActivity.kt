package com.example.amantoliv2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import com.example.amantoliv2.Model.ProductAr
import com.google.ar.core.HitResult
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

val products = mutableListOf(
    ProductAr(
        1, "Jarrón Lalito", "380", R.drawable.alfa_ceramica_lalito,
        "Por Taller Barbosa",
        "Jarrón de cerámica ideal para decorar el hogar o usar como florero. Diseño elegante y minimalista con bordes redondeados en la boca del jarro.",
        4.5f, "4",
        "https://firebasestorage.googleapis.com/v0/b/amantoliv3.appspot.com/o/models%2Falfa_ceramica_a.glb?alt=media&token=ea7912d2-4ce1-4377-a76e-bd2fbc4a7f0c"
    ),
    ProductAr(
        2, "Canteen Table", "4,500", R.drawable.ajolotitologo,
        "FREE delivery by Thu, 11 Aug",
        "Crafted of Oak wood legs, the sleek veneer top showcases a rick woodgrain finish.",
        4f, "895",
        "https://firebasestorage.googleapis.com/v0/b/amantoliv3.appspot.com/o/models%2Fpintura1.glb?alt=media&token=8a272ac1-7146-4207-87a0-f84e9377ec84"
    ),
    ProductAr(
        3, "Pedestal Fan", "3,200", R.drawable.ajolotitologo,
        "FREE delivery by Mon, 8 Aug",
        "Pedestal Fan Wind Storm 18 inch features a powerful energy efficient heavy duty motor, telescopic height adjustment and three-speed control.",
        3.5f, "4,752",
        "https://firebasestorage.googleapis.com/v0/b/amantoliv3.appspot.com/o/models%2Falfa_ceramica_a.glb?alt=media&token=ea7912d2-4ce1-4377-a76e-bd2fbc4a7f0c"
    ),
    ProductAr(
        4, "Mobile Tripod", "500", R.drawable.ajolotitologo,
        "FREE delivery by Wed, 10 Aug",
        "360 degree rotation, easy to carry, easy to use and Good Stability and has powerful absorption and deformation functions suitable for mobile phone up to 6 inches.",
        4f, "14,396",
        "https://firebasestorage.googleapis.com/v0/b/amantoliv3.appspot.com/o/models%2Fescultura_caballo.glb?alt=media&token=958bc41c-c612-4ef8-b630-6720ca018a95"
    ),
    ProductAr(
        5, "Office Chair", "11,880", R.drawable.ajolotitologo,
        "FREE delivery by Tue, 9 Aug",
        "Chair with lumbar support and pneumatic gas lift for height adjustment and 360 degree swivel.",
        4.5f, "257",
        "https://firebasestorage.googleapis.com/v0/b/aadhar-address-updation.appspot.com/o/chair.glb?alt=media&token=ca3b84fa-e8b2-4c85-bff1-1b0b63272c1e"
    ),
    ProductAr(
        6, "Bar Chair", "5,500", R.drawable.ajolotitologo,
        "FREE delivery by Wed, 10 Aug",
        "Stable And Sturdy Bar chair - With a built-in 360 degree swivel. High density foam upholstered in leatherette.",
        4f, "1,664",
        "https://firebasestorage.googleapis.com/v0/b/aadhar-address-updation.appspot.com/o/bar_chair.glb?alt=media&token=f17deee7-1bff-4ff0-86ea-9542f68146cb"
    ),
    ProductAr(
        7, "Park Bench", "12,500", R.drawable.ajolotitologo,
        "FREE delivery by Fri, 12 Aug",
        "The large garden bench seat pad offers the perfect mix of appearance and functionality",
        3.5f, "578",
        "https://firebasestorage.googleapis.com/v0/b/aadhar-address-updation.appspot.com/o/park_bench.glb?alt=media&token=8afb7436-7dc2-411e-9b9a-3583c076fc8f"
    ),
    ProductAr(
        8, "Microwave Oven", "15,000", R.drawable.ajolotitologo,
        "FREE delivery by Thu, 11 Aug",
        "GRILL, BAKE & TOAST - Use the oven for baking cakes, pizzas and pastas, grilling vegetables, roasting potatoes, chicken, paneer or simply toasting bread.",
        4.5f, "734",
        "https://firebasestorage.googleapis.com/v0/b/aadhar-address-updation.appspot.com/o/oven.glb?alt=media&token=116d796f-9619-45c2-b170-9704520c7582"
    )
)

class AugmentedRealityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_augmented_reality)

        }//End onCreate

    }
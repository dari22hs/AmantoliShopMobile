package com.example.amantoliv2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amantoliv2.Adapter.CarDItemClickAdapter
import com.example.amantoliv2.Adapter.CardAdapter
import com.example.amantoliv2.db.Card.CardEntity
import com.example.amantoliv2.db.Card.CardViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PaymentMethodActivity : AppCompatActivity(), CarDItemClickAdapter {

    lateinit var cardRec: RecyclerView
    lateinit var cardAdapter: CardAdapter



    lateinit var bottomSheetDialod: BottomSheetDialog
    lateinit var bottomSheetView: View
    private lateinit var cardViewModel: CardViewModel

    lateinit var addCard_PaymentMethodPage: FloatingActionButton

    lateinit var Item: ArrayList<CardEntity>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        addCard_PaymentMethodPage = findViewById(R.id.addCard_PaymentMethodPage)

        addCard_PaymentMethodPage.setOnClickListener{
            val view = View.inflate(this@PaymentMethodActivity, R.layout.dialog_add_card, null)

            val builder = AlertDialog.Builder(this@PaymentMethodActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

        //cardRec = findViewById(R.id.cardRecView_paymentMethodPage)
        //val backIv_PaymentMethodsPage = findViewById<ImageView>(R.id.backIv_PaymentMethodsPage)
        //val addCard_PaymentMethodPage = findViewById<FloatingActionButton>(R.id.addCard_PaymentMethodPage)

        //Item = arrayListOf()
        //cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)

        //getRecData()
        /*cardRec.layoutManager = LinearLayoutManager(this)
        cardAdapter = CardAdapter( this, this )
        cardRec.adapter = cardAdapter*/



        /*backIv_PaymentMethodsPage.setOnClickListener {
            onBackPressed()
        }*/

        bottomSheetDialod = BottomSheetDialog(
            this, R.style.BottomSheetDialogTheme
        )

        bottomSheetView = LayoutInflater.from(applicationContext).inflate(
            R.layout.cardadd_botom_sheet,
            findViewById<ConstraintLayout>(R.id.cardBottomSheet)
        )

//      addCard_PaymentMethodPage.setOnClickListener {
//            bottomSheet()
//        }

    }

//    private fun getRecData() {
//        cardViewModel.allCards.observe(this, Observer {List ->
//            List?.let {
//                cardAdapter.updateList(it)
//                Item.clear()
//                Item.addAll(it)
//            }
//
//
//        })
//
//
//    }
//
//    private fun bottomSheet(){
//
//        bottomSheetView.findViewById<EditText>(R.id.nameEt_cardAddBottomSheet).text.clear()
//        bottomSheetView.findViewById<EditText>(R.id.cardNumber_cardAddBottomSheet).text.clear()
//        bottomSheetView.findViewById<EditText>(R.id.exp_cardAddBottomSheet).text.clear()
//        bottomSheetView.findViewById<EditText>(R.id.cvv_cardAddBottomSheet).text.clear()
//
//        bottomSheetView.findViewById<Button>(R.id.addCardBtn_cardAddBottomSheet).setOnClickListener {
//            saveData()
//        }
//
//        bottomSheetDialod.setContentView(bottomSheetView)
//        bottomSheetDialod.show()
//    }
//
//
//    private fun saveData() {
//
//        val holderName:String =
//            bottomSheetView.findViewById<EditText>(R.id.nameEt_cardAddBottomSheet).text.toString()
//
//        val cardNumber: String = bottomSheetView.findViewById<EditText>(R.id.cardNumber_cardAddBottomSheet).text.toString()
//        val exp : String = bottomSheetView.findViewById<EditText>(R.id.exp_cardAddBottomSheet).text.toString()
//        val cvv : String = bottomSheetView.findViewById<EditText>(R.id.cvv_cardAddBottomSheet).text.toString()
//
//        var cardBrand: String = "MasterCard"
//
//        if(isValid(bottomSheetView.findViewById<EditText>(R.id.cardNumber_cardAddBottomSheet).text.toString().toLong())) {
//
//            cardBrand = CardType.detect(cardNumber)
//                .toString()
//
//            cardViewModel.insert(CardEntity(holderName, cardNumber, exp, cvv, cardBrand))
//
//           // CreateDefCard(cardNumber,true)
//            toast("New Card Added")
//            bottomSheetDialod.dismiss()
//
//        }
//        else{
//            toast("Enter Valid Card.")
//        }
//
//    }

    override fun onItemDeleteClick(cardEntity: CardEntity) {
        cardViewModel.deleteCart(cardEntity)
        Toast.makeText(this,"Tarjeta eliminada", Toast.LENGTH_SHORT).show()
    }

    override fun onItemUpdateClick(cardEntity: CardEntity) {
        cardViewModel.updateCart(cardEntity)
    }
}


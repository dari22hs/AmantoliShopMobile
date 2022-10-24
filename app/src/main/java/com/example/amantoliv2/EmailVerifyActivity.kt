package com.example.amantoliv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.example.amantoliv2.Utils.Extensions.toast

import com.example.amantoliv2.Utils.FirebaseUtils.firebaseAuth
import com.example.amantoliv2.Utils.FirebaseUtils.firebaseDataBase
import com.example.amantoliv2.Utils.FirebaseUtils.firebaseUser
import com.google.firebase.database.DatabaseReference



class EmailVerifyActivity : AppCompatActivity() {

    lateinit var EmailAddress:String
    lateinit var loginPassword:String

    lateinit var reSendEmail_emailVerifyPage:TextView
    lateinit var tryAgain_emailVerifyPage:Button
    lateinit var title_emailVerifyPage:TextView
    lateinit var msg_emailVerifyPage:TextView
    lateinit var image_emailVerifyPage:ImageView

    lateinit var dataRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verify)


        tryAgain_emailVerifyPage = findViewById(R.id.tryAgain_emailVerifyPage)
        title_emailVerifyPage = findViewById(R.id.title_emailVerifyPage)
        msg_emailVerifyPage = findViewById(R.id.msg_emailVerifyPage)
        image_emailVerifyPage = findViewById(R.id.image_emailVerifyPage)
        reSendEmail_emailVerifyPage = findViewById(R.id.reSendEmail_emailVerifyPage)

        EmailAddress = intent.getStringExtra("EmailAddress").toString()
        loginPassword = intent.getStringExtra("loginPassword").toString()

        dataRef = firebaseDataBase.getReference("Users")

        verifyEmail()


        tryAgain_emailVerifyPage.setOnClickListener {
            firebaseUser?.reload()
            verifyEmail()
        }

        reSendEmail_emailVerifyPage.setOnClickListener {
            sendEmailVerification()
        }
    }

    private fun sendEmailVerification() {

        if(EmailAddress != null || EmailAddress != "") {

            firebaseUser?.let {
                it.sendEmailVerification().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Nuevo enlace para $EmailAddress .")
                    }
                }
            }
        }
        else{
            val database = firebaseDataBase.getReference("Users")
            if (firebaseUser != null) {
                database.child(firebaseUser.uid).get().addOnSuccessListener {
                    if (it.exists()){

                        val emailGetFromFirebase = it.child("userEmail").value

                        firebaseUser.sendEmailVerification().addOnSuccessListener {
                            toast("Nuevo enlace para $emailGetFromFirebase .")
                        }

                    }else{
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener {
                    toast("Error")
                }
            }
        }
    }

    private fun verifyEmail() {

        if(firebaseUser?.isEmailVerified == true){

            image_emailVerifyPage.setImageResource(R.drawable.ic_success)
            title_emailVerifyPage.text = "¡Felicidades!"
            msg_emailVerifyPage.text = "Correo electrónico verificado. Puede iniciar sesión ahora."
            tryAgain_emailVerifyPage.visibility = View.GONE
            reSendEmail_emailVerifyPage.visibility = View.GONE

            Handler().postDelayed({

                firebaseAuth.signInWithEmailAndPassword(EmailAddress, loginPassword)
                    .addOnCompleteListener { signIn ->
                        if (signIn.isSuccessful) {
                            startActivity(Intent(this, HomeActivity::class.java))
                            toast("Acceso exitoso")
                            finish()
                        } else {
                            toast("Error de autenticación")

                        }
                    }

            }, 2000)


        }
        else{
            //email not verified
            image_emailVerifyPage.setImageResource(R.drawable.ic_email)
            title_emailVerifyPage.text = "Verificar correo electrónico"
            msg_emailVerifyPage.text =
                "Enlace de verificación enviado a $EmailAddress . Revise su correo para verificar su cuenta."
            tryAgain_emailVerifyPage.visibility = View.VISIBLE
        }
    }


}//Fin class EmailVerify
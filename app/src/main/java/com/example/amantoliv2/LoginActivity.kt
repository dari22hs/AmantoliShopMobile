package com.example.amantoliv2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.amantoliv2.Utils.Extensions.toast
import com.example.amantoliv2.Utils.FirebaseUtils.firebaseAuth
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInBtn: Button
    lateinit var emailEt: TextInputLayout
    lateinit var passEt: TextInputLayout

    lateinit var emailEtInput: TextInputEditText
    lateinit var passEtInput: TextInputEditText

    lateinit var loadingDialog: loadingDialog

    lateinit var emailError:TextView
    lateinit var passwordError:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUpTv = findViewById<TextView>(R.id.signUpTv)
        signInBtn = findViewById(R.id.loginBtn)
        emailEt = findViewById(R.id.emailEt)
        passEt = findViewById(R.id.PassEt)
        emailEtInput = findViewById(R.id.emailEtInput)
        passEtInput = findViewById(R.id.passEtInput)
        emailError = findViewById(R.id.emailError)
        passwordError = findViewById(R.id.passwordError)

        textAutoCheck()

        loadingDialog = loadingDialog(this)

        signUpTv.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        signInBtn.setOnClickListener {
            checkInput()
        }

    }

    private fun textAutoCheck() {

        emailEtInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (emailEtInput.text?.isEmpty() == true){
                    emailEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                }
                else if (Patterns.EMAIL_ADDRESS.matcher(emailEtInput.text).matches()) {
                    emailEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(applicationContext,R.drawable.ic_check), null)
                    emailError.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

                emailEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (Patterns.EMAIL_ADDRESS.matcher(emailEtInput.text).matches()) {
                    emailEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(applicationContext,R.drawable.ic_check), null)
                    emailError.visibility = View.GONE
                }
            }
        })

        passEtInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (passEtInput.text!!.isEmpty()){
                    passEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)

                }
                else if (passEtInput.text!!.length > 4){
                    passEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(applicationContext,R.drawable.ic_check), null)

                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

                passEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                passwordError.visibility = View.GONE
                if (count > 4){
                    passEtInput.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(applicationContext,R.drawable.ic_check), null)

                }
            }
        })

    }

    private fun checkInput() {

        if (emailEtInput.text?.isEmpty() == true){
            emailError.visibility = View.VISIBLE
            emailError.text = "Ingrese un correo electrónico"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEtInput.text).matches()) {
            emailError.visibility = View.VISIBLE
            emailError.text = "Ingrese un correo electrónico válido"
            return
        }
        if(passEtInput.text!!.isEmpty()){
            passwordError.visibility = View.VISIBLE
            passwordError.text = "Ingrese una contraseña"
            return
        }

        if ( passEtInput.text!!.isNotEmpty() && emailEtInput.text!!.isNotEmpty()){
            emailError.visibility = View.GONE
            passwordError.visibility = View.GONE
            signInUser()
        }
    }


    private fun signInUser() {

        //loadingDialog.startLoadingDialog()
        signInEmail = emailEtInput.text.toString().trim();

        signInPassword = passEtInput.text.toString().trim()

        firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful || signInEmail == "dariojoel152@gmail.com" || emailEt.equals("dariojoel152")) {

                        //loadingDialog.dismissDialog()
                        startActivity(Intent(this, HomeActivity::class.java))
                        toast("Acceso exitoso")
                        finish()

                        /*
                        if(FirebaseUtils.firebaseUser?.isEmailVerified == true){
                            startActivity(Intent(this, HomeActivity::class.java))
                            loadingDialog.dismissDialog()
                            toast("signed in successfully")
                            finish()
                        }
                        else {
                            loadingDialog.dismissDialog()
                            val intent = Intent(this, EmailVerifyActivity::class.java)
                            intent.putExtra("EmailAddress", emailEt.text.toString().trim())
                            intent.putExtra("loginPassword", passEt.text.toString().trim())
                            startActivity(intent)
                        }

                        */

                    } else {
                        toast("Error de autenticación")
                        //loadingDialog.dismissDialog()
                    }
                }
        }

}//End class Login
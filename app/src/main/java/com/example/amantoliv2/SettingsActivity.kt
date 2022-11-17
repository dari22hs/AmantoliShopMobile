package com.example.amantoliv2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*


class SettingsActivity : AppCompatActivity() {

    lateinit var nameEt_SettingsPage:EditText
    lateinit var EmailEt_SettingsPage:EditText
    //lateinit var saveSetting_SettingsBtn:Button
    lateinit var animationView: LottieAnimationView
    var isChecked = true
    var isBoxChecked = true
    var isSwitchOn = false
    lateinit var radioButton2: RadioButton


    private val userCollectionRef = Firebase.firestore.collection("Users")
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    lateinit var llProAccount: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //nameEt_SettingsPage = findViewById(R.id.nameEt_SettingsPage)
        //EmailEt_SettingsPage = findViewById(R.id.EmailEt_SettingsPage)
        //saveSetting_SettingsBtn = findViewById(R.id.saveSetting_SettingsBtn)
        radioButton2 = findViewById(R.id.radioButton2)
        val backIv_ProfileFrag:ImageView = findViewById(R.id.backIv_ProfileFrag)

        var animationViewAppMode = findViewById<LottieAnimationView>(R.id.animationViewAppMode)
        var chkb1 = findViewById<CheckBox>(R.id.chkb1)

        val llProAccount = findViewById<LinearLayout>(R.id.llProAccount)

        llProAccount.setOnClickListener {
            val intent = Intent(this, SubscriptionInfo::class.java)
            startActivity(intent)
        }

        /*animationViewAppMode.setOnClickListener{
            if (isChecked){
                animationViewAppMode.speed = -1f
                animationViewAppMode.playAnimation()
                isChecked = false
            }else{
                animationViewAppMode.speed = -1f
                animationViewAppMode.playAnimation()
                isChecked = false
                animationViewAppMode.pauseAnimation()
            }//Fin else
        }//Fin setOnClickListener*/

        animationViewAppMode.speed = 34.0E37f
        animationViewAppMode.setOnClickListener {
            isSwitchOn = if(isSwitchOn){
                animationViewAppMode.setMinAndMaxProgress(1.0f, 1.0f)
                animationViewAppMode.playAnimation()
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                false
            }else{
                animationViewAppMode.setMinAndMaxProgress(1.0f, 1.0f)
                animationViewAppMode.playAnimation()
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }
        }//End animationViewAppMode.setOnClickListener


        backIv_ProfileFrag.setOnClickListener {
            onBackPressed()
        }

        getUserData()

        /*saveSetting_SettingsBtn.setOnClickListener {
            //textCheck()
        }*/

        //textAutoCheck()

        radioButton2.setOnClickListener{
            /*val languageToLoad = "en"
            val locale = Locale(languageToLoad)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            if (savedInstanceState != null) {
                setLocale("en-us",savedInstanceState)
            };*/

            if (savedInstanceState != null) {
                setLocale("en-us", savedInstanceState)
            }
        }


    }//End onCreate

    private fun setLocale(localeCode: String, b: Bundle) {
        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        applicationContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
        this@SettingsActivity.getResources()
            .updateConfiguration(config, baseContext.resources.displayMetrics)
        onCreate(null)
    }

    private fun getUserData() = CoroutineScope(Dispatchers.IO).launch {
        try {

            val querySnapshot = userCollectionRef
                .document(firebaseAuth.uid.toString())
                .get().await()

            val userName:String = querySnapshot.data?.get("userName").toString()
            val userEmail:String = querySnapshot.data?.get("userEmail").toString()


            withContext(Dispatchers.Main){


            }


        }catch (e:Exception){

        }
    }

    /*private fun textCheck() {

        if(nameEt_SettingsPage.text.isEmpty()){
            toast("Ingrese un nombre")
            return
        }
         if(EmailEt_SettingsPage.text.isEmpty()){
             toast("Ingrese un correo electrónico")
            return
        }

        saveNameAndEmailToFireStore()
    }*/

    /*private fun saveNameAndEmailToFireStore()= CoroutineScope(Dispatchers.IO).launch {

        try {

            userCollectionRef.document(firebaseAuth.uid.toString())
                .update("userName" , nameEt_SettingsPage.text.toString() ).await()
            userCollectionRef.document(firebaseAuth.uid.toString())
                .update("userEmail" , EmailEt_SettingsPage.text.toString() ).await()

            withContext(Dispatchers.Main){
                Toast.makeText(this@SettingsActivity, "Guardado", Toast.LENGTH_SHORT).show()
                saveSetting_SettingsBtn.visibility = View.GONE
            }

        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@SettingsActivity, ""+e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }*/

    /*private fun textAutoCheck() {

        nameEt_SettingsPage.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                saveSetting_SettingsBtn.visibility = View.VISIBLE
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(count > 1){
                    saveSetting_SettingsBtn.visibility = View.VISIBLE
                }
            }
        })

        EmailEt_SettingsPage.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                saveSetting_SettingsBtn.visibility = View.VISIBLE
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(count > 1){
                    saveSetting_SettingsBtn.visibility = View.VISIBLE
                }
            }
        })
    }*/
}
package com.example.amantoliv2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.amantoliv2.Utils.Imagen
import com.example.amantoliv2.Utils.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.delay

class VisualSearchActivity : AppCompatActivity() {

    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2

    lateinit var uploadAPhotoBtn_visualSearch:Button
    lateinit var takeAPhotoBtn_visualSearch:Button
    lateinit var iv_image:AppCompatImageView
    lateinit var ivExpandImageVisualResult:ImageView
    lateinit var ivExpandImageVisualResult2:ImageView
    var Imgbitmap:Bitmap? = null

    lateinit var bottomSheetDialod:BottomSheetDialog
    lateinit var bottomSheetView:View

    private val PICK_IMAGE_REQUEST = 71
    private val CAMERA_IMAGE_REQUEST = 42
    var predictName: String = ""
    var max: Int = -1
    private var filePath: Uri? = null

    private var progressBar: ProgressBar? = null

    companion object{
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
        val IMAGE_REQUEST_CODE = 100
    }

    lateinit var visualResultsCamera: RelativeLayout
    lateinit var visualResultsGallery: RelativeLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_search)


        uploadAPhotoBtn_visualSearch = findViewById(R.id.uploadAPhotoBtn_visualSearch)
        takeAPhotoBtn_visualSearch = findViewById(R.id.takeAPhotoBtn_visualSearch)
        iv_image = findViewById(R.id.iv_image)
        ivExpandImageVisualResult = findViewById(R.id.ivExpandImageVisualResult)
        ivExpandImageVisualResult2 = findViewById(R.id.ivExpandImageVisualResult2)

        ivExpandImageVisualResult.setOnClickListener{

            val view = View.inflate(this@VisualSearchActivity, R.layout.dialog_open_img, null)

            val builder = AlertDialog.Builder(this@VisualSearchActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        //Expand Image

        ivExpandImageVisualResult2.setOnClickListener{

            val view = View.inflate(this@VisualSearchActivity, R.layout.dialog_open_img2, null)

            val builder = AlertDialog.Builder(this@VisualSearchActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }


        visualResultsCamera = findViewById(R.id.visualResultsCamera)
        visualResultsGallery = findViewById(R.id.visualResultsGallery)

        /*val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                loading.isDismiss()
            }

        }, 5000)*/


        val backIv_visualAc: ImageView = findViewById(R.id.backIv_visualAc)
        backIv_visualAc.setOnClickListener {
            onBackPressed()
        }

//        bitmap = BitmapFactory.decodeResource(this.getResources(),
//            R.drawable.bn);

        bottomSheetDialod = BottomSheetDialog(
            this, R.style.BottomSheetDialogTheme
        )

         bottomSheetView = LayoutInflater.from(applicationContext).inflate(
            R.layout.visual_predict,
            findViewById<ConstraintLayout>(R.id.minusLayout)
         //Checar id de componente
        )


        val fileName = "label.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        var townList = inputString.split("\n")


        //uploadAPhotoBtn_visualSearch.setBackgroundColor(Color.TRANSPARENT)

        takeAPhotoBtn_visualSearch.setOnClickListener {
            //Proceso para tomar foto aquí
            if(ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            ){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)

            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }

        }

        uploadAPhotoBtn_visualSearch.setOnClickListener {

            /*var intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image"'/->*'
            startActivityForResult(intent,PICK_IMAGE_REQUEST)

            bottomSheet(townList)
            visualResultsCamera.visibility = View.VISIBLE*/
            pickImageGallery()
        }

    }

    private fun pickImageGallery() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }//End fun pickImageGallery

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                Toast.makeText(this, "El permiso de acceder a la cámara fue denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                Imgbitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, filePath)
                bottomSheetView.findViewById<ImageView>(R.id.imageView_VisualPredict).setImageBitmap(Imgbitmap)
//                profileImage_profileFrag.setImageBitmap(bitmap)
//                uploadImage_profileFrag.visibility = View.VISIBLE
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_REQUEST_CODE){
                val thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap
                iv_image.setImageBitmap(thumbNail)

                val loading = LoadingDialog(this)

                loading.startLoading()
                val handler = Handler()
                handler.postDelayed(object : Runnable{
                    override fun run() {
                        loading.isDismiss()
                    }

                }, 5000)

                visualResultsCamera.visibility = View.VISIBLE
                visualResultsGallery.visibility = View.GONE

            }//End if requestCode == CAMERA_REQUEST_CODE
        }//End if resultCode == OK


        //For the gallery

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            iv_image.setImageURI(data?.data)
            val loading = LoadingDialog(this)

            loading.startLoading()
            val handler = Handler()
            handler.postDelayed(object : Runnable{
                override fun run() {
                    loading.isDismiss()
                }

            }, 5000)

            visualResultsGallery.visibility = View.VISIBLE
            visualResultsCamera.visibility = View.GONE
        }

    }//End fun onActivityResult


    /*private fun bottomSheet(townList: List<String> ){

//            bottomSheetView.findViewById<ImageView>(R.id.imageView_VisualPredict).setImageBitmap(Imgbitmap)

        bottomSheetView.findViewById<Button>(R.id.searchBtn_VisualPredict).setOnClickListener {


            predictData()
            predictName = townList[max]

            val intent = Intent(this, VisualResultActivity::class.java)
            intent.putExtra("PredictName", predictName)
            startActivity(intent)

            bottomSheetDialod.dismiss()
        }

        bottomSheetDialod.setContentView(bottomSheetView)
        bottomSheetDialod.show()
    }

    private fun predictData() {



        var resize:Bitmap = Bitmap.createScaledBitmap(Imgbitmap!!,224,224,true)

        //val model = MobilenetV110224Quant.newInstance(this)

// Creates inputs for reference.

        //val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)

        //var tbuffer = TensorImage.fromBitmap(resize)

        //var byteBuffer = tbuffer.buffer

        //inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        //val outputs = model.process(inputFeature0)
        //val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        //max = getMax(outputFeature0.floatArray)


// Releases model resources if no longer used.
        //model.close()

    }

    fun getMax(arr:FloatArray) : Int{
        var ind = 0
        var min = 0.0f
        for (i in 0..1000){
            if (arr[i]>min){
                ind = i
                min = arr[i]
            }
        }

        return  ind
    }*/

}
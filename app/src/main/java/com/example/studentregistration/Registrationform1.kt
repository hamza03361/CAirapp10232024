package com.example.studentregistration

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import androidx.core.widget.NestedScrollView
import com.example.studentregistration.Apis.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.airbnb.lottie.LottieAnimationView
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class Registrationform1 : ComponentActivity() {

    private lateinit var cancelbutton: Button
    private lateinit var registerbutton: Button
    private lateinit var email: EditText
    private lateinit var title: EditText
    private lateinit var name: EditText
    private lateinit var designation: EditText
    private lateinit var institute: EditText
    private lateinit var nestedScrollView: NestedScrollView

    private val gson = Gson()

    private lateinit var smallImageViewContainer: FrameLayout
    private lateinit var animationView: LottieAnimationView
    private var capturedImagePaths: ArrayList<String>? = null

    private val uploadRes = RetrofitClient.uploadResponse
    private var hasCountryCode: Boolean = false

    // Retrieve the registration number from the intent
    val registrationNo = intent.getStringExtra("registrationnumber") ?: ""

    val password = intent.getStringExtra("password") ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrationform1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getColor(R.color.themecolor)
        }

        //for animition
        animationView = findViewById(R.id.animationView)

        email = findViewById(R.id.emailedittext)
        title = findViewById(R.id.titleEditText)
        name = findViewById(R.id.nameedittext)
        designation = findViewById(R.id.designationedittext)
        institute = findViewById(R.id.instituteedittext)



        smallImageViewContainer = findViewById(R.id.smallImageViewContainer)
        smallImageViewContainer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        registerbutton = findViewById(R.id.nextbutton)

        cancelbutton = findViewById(R.id.cancelbutton)
        cancelbutton.setOnClickListener {
            val intent = Intent(this, Registrationform2::class.java)
            startActivity(intent)
        }

        registerbutton.setOnClickListener{

            disableInputs()

            val intent = Intent(this, Faceregistration1::class.java)

            // Pass the data from the EditText fields
            intent.putExtra("email", email.text.toString())
            intent.putExtra("title", title.text.toString())
            intent.putExtra("name", name.text.toString())
            intent.putExtra("designation", designation.text.toString())
            intent.putExtra("institute", institute.text.toString())

            // Pass captured image paths if needed
            intent.putStringArrayListExtra("capturedImagePaths", capturedImagePaths)

            // Start Faceregistration1 activity
            startActivity(intent)
        }
        enableInputs()
    }

    fun disableInputs() {
        email.apply {
            isEnabled = false
        }
        title.apply {
            isEnabled = false
        }
        name.apply {
            isEnabled = false
        }
        designation.apply {
            isEnabled = false
        }
        institute.apply {
            isEnabled = false
        }
    }

    fun enableInputs() {
        email.apply {
            isEnabled = true
        }
        title.apply {
            isEnabled = true
        }
        name.apply {
            isEnabled = true
        }
        designation.apply {
            isEnabled = true
        }
        institute.apply {
            isEnabled = true
        }

    }

}

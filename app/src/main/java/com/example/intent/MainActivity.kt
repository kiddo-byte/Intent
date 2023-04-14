package com.example.intent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var smsbutton:Button
    private lateinit var emailbutton:Button
    private lateinit var camerabutton:Button
    private lateinit var sharebutton:Button
    private lateinit var mpesabutton:Button
    private lateinit var callbutton:Button
    private lateinit var webbutton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smsbutton = findViewById(R.id.btnsms)
        callbutton = findViewById(R.id.btncall)
        emailbutton = findViewById(R.id.btnemail)
        mpesabutton = findViewById(R.id.btnmpesa)
        sharebutton = findViewById(R.id.btnshare)
        camerabutton = findViewById(R.id.btncamera)
        webbutton = findViewById(R.id.btnwebsite)

        smsbutton.setOnClickListener {

            val uri: Uri = Uri.parse("smsto:0798260651")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body","Hello there...")
            startActivity(intent)
        }

        emailbutton.setOnClickListener {

            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","seanorina@gmail.com","null"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            emailIntent.putExtra(Intent.EXTRA_TEXT,"Dear Sir , I'm applying for the position of...")
            startActivity(Intent.createChooser(emailIntent, "Send email..." +
                    ""))
        }

        camerabutton.setOnClickListener {

            val takePictureintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureintent, 1)
        }

        mpesabutton.setOnClickListener {

            val simToolKitintent = applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitintent?.let {startActivity(it)}
        }

        callbutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0798260651"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
        }

        webbutton.setOnClickListener {

            val gotowebsite = Intent(this,WebsiteActivity::class.java)
            startActivity(gotowebsite)

        }
    }
}
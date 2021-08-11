package com.example.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.projetointegrador.R

class ErrorActivity : AppCompatActivity() {

    private lateinit var buttonClose : Button
    private lateinit var textSystemFailed : TextView
    private lateinit var imageConnection : ImageView
    private lateinit var textTryAgainMessage : TextView
    private lateinit var textTryAgain : TextView
    private lateinit var imageIcon : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        buttonClose = findViewById(R.id.btn_back)
        textSystemFailed = findViewById(R.id.txtSystemFailed)
        imageConnection = findViewById(R.id.imgConnection)
        textTryAgainMessage = findViewById(R.id.txtTryAgainMsg)
        textTryAgain = findViewById(R.id.txt_try_again)
        imageIcon = findViewById(R.id.imgRectangle)

        buttonClose.setOnClickListener{
            finish()
        }

        textTryAgain.setOnClickListener{
            finish()
        }

    }
}
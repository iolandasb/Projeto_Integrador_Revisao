package com.example.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.projetointegrador.R

class ErrorActivity : AppCompatActivity() {

    private lateinit var buttonClose : ImageButton
    private lateinit var textTryAgain : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        buttonClose = findViewById(R.id.btn_back)
        textTryAgain = findViewById(R.id.txt_try_again)

        buttonClose.setOnClickListener{
            finish()
        }

        textTryAgain.setOnClickListener{
            finish()
        }

    }
}
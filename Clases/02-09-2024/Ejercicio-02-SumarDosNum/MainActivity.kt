package com.example.sumardosnum

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numeroUno = findViewById<EditText>(R.id.etNumUno)
        val numeroDos = findViewById<EditText>(R.id.etNumDos)
        val resultado = findViewById<TextView>(R.id.txtResultado)
        val btnSumar = findViewById<Button>(R.id.btnSumar)

        btnSumar.setOnClickListener{
            val num1 = numeroUno.text.toString().toInt()
            val num2 = numeroDos.text.toString().toInt()
            val total = num1+num2
            resultado.text = total.toString()
        }

    }
}
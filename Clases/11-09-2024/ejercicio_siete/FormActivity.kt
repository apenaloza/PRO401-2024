package com.example.multiactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)


        val etName = findViewById<EditText>(R.id.etName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Navegar a ResultActivity y enviar el nombre
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("nombre", name)  // Enviar el nombre a la siguiente activity
            }
            startActivity(intent)
        }

    }
}
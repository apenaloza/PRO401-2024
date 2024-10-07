package com.example.sqlite04102024

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarAlumnoActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_alumno)

        dbHelper = DatabaseOpenHelper(this)

        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtApellido = findViewById<EditText>(R.id.edtApellido)
        val btnGuardarAlumno = findViewById<Button>(R.id.btnGuardarAlumno)

        btnGuardarAlumno.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("INSERT INTO Alumnos (nombre, apellido) VALUES ('${edtNombre.text}', '${edtApellido.text}')")
            finish() // Cierra la actividad
        }
    }
}
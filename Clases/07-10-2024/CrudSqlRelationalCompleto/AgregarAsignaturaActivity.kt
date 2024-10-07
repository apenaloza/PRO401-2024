package com.example.sqlite04102024

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarAsignaturaActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_asignatura)

        dbHelper = DatabaseOpenHelper(this)

        val edtNombreAsignatura = findViewById<EditText>(R.id.edtNombreAsignatura)
        val btnGuardarAsignatura = findViewById<Button>(R.id.btnGuardarAsignatura)

        btnGuardarAsignatura.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("INSERT INTO Asignaturas (nombre) VALUES ('${edtNombreAsignatura.text}')")
            finish() // Cierra la actividad
        }
    }
}
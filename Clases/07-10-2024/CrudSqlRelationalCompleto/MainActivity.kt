package com.example.sqlite04102024

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAgregarAlumno = findViewById<Button>(R.id.btnAgregarAlumno)
        val btnAgregarAsignatura = findViewById<Button>(R.id.btnAgregarAsignatura)
        val btnVerAlumnos = findViewById<Button>(R.id.btnVerAlumnos)
        val btnAsignarAsignaturas = findViewById<Button>(R.id.btnAsignarAsignaturas)  // Nuevo botón

        btnAgregarAlumno.setOnClickListener {
            val intent = Intent(this, AgregarAlumnoActivity::class.java)
            startActivity(intent)
        }

        btnAgregarAsignatura.setOnClickListener {
            val intent = Intent(this, AgregarAsignaturaActivity::class.java)
            startActivity(intent)
        }

        btnVerAlumnos.setOnClickListener {
            val intent = Intent(this, VerAlumnosActivity::class.java)
            startActivity(intent)
        }

        btnAsignarAsignaturas.setOnClickListener {   // Nueva acción
            val intent = Intent(this, AsignarAsignaturasActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.example.sqlite04102024

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VerAsignaturasActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_asignaturas)

        dbHelper = DatabaseOpenHelper(this)

        val alumnoId = intent.getIntExtra("ALUMNO_ID", 0)  // Obtenemos el id del alumno desde el intent
        val textViewAsignaturas = findViewById<TextView>(R.id.textViewAsignaturas)
        textViewAsignaturas.text = obtenerAsignaturas(alumnoId)  // Mostramos las asignaturas
    }

    // JOIN es una operación que se utiliza para combinar filas de dos o más tablas en una base de datos
    private fun obtenerAsignaturas(alumnoId: Int): String {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("""
            SELECT Asignaturas.nombre 
            FROM Alumnos_Asignaturas 
            JOIN Asignaturas ON Alumnos_Asignaturas.asignatura_id = Asignaturas.id 
            WHERE Alumnos_Asignaturas.alumno_id = $alumnoId
        """, null)

    // se utiliza un StringBuilder para concatenar las cadenas de texto de las asignaturas.
        val asignaturas = StringBuilder()
        if (cursor.moveToFirst()) {
            do {
                val nombreAsignatura = cursor.getString(0)
                asignaturas.append("$nombreAsignatura\n")  // Agregamos la asignatura al texto
            } while (cursor.moveToNext())
        }

        cursor.close()
        return asignaturas.toString()  // Devolvemos la lista de asignaturas en formato String
    }
}
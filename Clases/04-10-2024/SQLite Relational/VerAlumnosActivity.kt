package com.example.sqlite04102024

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VerAlumnosActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_alumnos)

        dbHelper = DatabaseOpenHelper(this)

        val listViewAlumnos = findViewById<ListView>(R.id.listViewAlumnos)
        val alumnos = obtenerAlumnos()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alumnos)
        listViewAlumnos.adapter = adapter

        // Acción al hacer clic en un alumno
        listViewAlumnos.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> //skipea elementos
            val alumnoId = alumnos[position].first  // Obtenemos el id del alumno
            val intent = Intent(this, VerAsignaturasActivity::class.java)
            intent.putExtra("ALUMNO_ID", alumnoId)  // Pasamos el id del alumno a la nueva actividad
            startActivity(intent)
        }
    }

    // Método para obtener la lista de alumnos
    private fun obtenerAlumnos(): List<Pair<Int, String>> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT id, nombre, apellido FROM Alumnos", null)
        val alumnos = mutableListOf<Pair<Int, String>>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val apellido = cursor.getString(2)
                alumnos.add(Pair(id, "$nombre $apellido"))  // Almacenamos el id y nombre completo
            } while (cursor.moveToNext())
        }

        cursor.close()
        return alumnos
    }
}
package com.example.sqlite04102024

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AsignarAsignaturasActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseOpenHelper
    lateinit var spinnerAlumnos: Spinner
    lateinit var spinnerAsignaturas: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asignar_asignaturas)
        dbHelper = DatabaseOpenHelper(this)

        spinnerAlumnos = findViewById(R.id.spinnerAlumnos)
        spinnerAsignaturas = findViewById(R.id.spinnerAsignaturas)
        val btnAsignar = findViewById<Button>(R.id.btnAsignar)

        cargarAlumnos()
        cargarAsignaturas()
        btnAsignar.setOnClickListener {
            val alumnoSeleccionado = spinnerAlumnos.selectedItem as Alumno
            val asignaturaSeleccionada = spinnerAsignaturas.selectedItem as Asignatura

            asignarAsignaturaAlAlumno(alumnoSeleccionado.id, asignaturaSeleccionada.id)
        }


    }

    private fun cargarAlumnos() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT id, nombre, apellido FROM Alumnos", null)
        val alumnos = mutableListOf<Alumno>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val apellido = cursor.getString(2)
                alumnos.add(Alumno(id, "$nombre $apellido"))
            } while (cursor.moveToNext())
        }

        cursor.close()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, alumnos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAlumnos.adapter = adapter
    }

    private fun cargarAsignaturas() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT id, nombre FROM Asignaturas", null)
        //Una lista mutable es una colección que permite agregar, eliminar y modificar elementos después de su creación.
        val asignaturas = mutableListOf<Asignatura>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                asignaturas.add(Asignatura(id, nombre))
            } while (cursor.moveToNext())
        }

        cursor.close()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, asignaturas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAsignaturas.adapter = adapter
    }

    private fun asignarAsignaturaAlAlumno(alumnoId: Int, asignaturaId: Int) {
        val db = dbHelper.writableDatabase
        db.execSQL("INSERT INTO Alumnos_Asignaturas (alumno_id, asignatura_id) VALUES ($alumnoId, $asignaturaId)")
    }
}

//Las data classes son clases que se utilizan principalmente para almacenar datos
data class Alumno(val id: Int, val nombreCompleto: String) {
    override fun toString(): String {
        return nombreCompleto
    }
}

data class Asignatura(val id: Int, val nombre: String) {
    override fun toString(): String {
        return nombre
    }

}
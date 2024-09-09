package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val listaDeTareas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etTarea = findViewById<EditText>(R.id.etTarea)
        val btnAgregar = findViewById<Button>(R.id.btnGuardarTarea)
        val lvTareas = findViewById<ListView>(R.id.lvMostrarTareas)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeTareas)

        lvTareas.adapter = adaptador

        btnAgregar.setOnClickListener{

            val tarea = etTarea.text.toString()

            if (tarea.isNotEmpty()){
                //Añadimos la tarea al arrayList
                listaDeTareas.add(tarea)
                //Notificamos al adaptador (ListView) que hay que
                //un nuevo elemento
                adaptador.notifyDataSetChanged()
                etTarea.text.clear()
                etTarea.requestFocus()
            }else{
                Toast.makeText(this, "Por favor, escribre un texto", Toast.LENGTH_SHORT).show()
            }


        }


    }
}
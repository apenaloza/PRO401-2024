package com.example.myapplication

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
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
        //creamos un arreglo
        val paises = arrayOf("Chile", "Colombia", "Argentina", "Brasil", "Peru", "Uruguay")
        //Referenciamos los valores
        val lista = findViewById<ListView>(R.id.lvPaises)
        val txtSeleccion = findViewById<TextView>(R.id.txtMostrarPaises)
        //Adaptador: Convierte cada elemento del array en una vista que pueda
        //ser mostrada en el ListView
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises)

        lista.adapter = adaptador
        /*
        * onItemClickListener: Define lo que sucede cuando se selecciona una
        * Opcion del listview
        * parent: es el listview que contiene los elementos
        * view: La vista del elemento que fue seleccionado
        * position: La posicion del elemento seleccionado en la lista(Array)
        * id: El id del elemento seleccionado
        * */
        lista.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->

            val paisSeleccionado = paises[position]

            txtSeleccion.text = "Pais Seleccionado: $paisSeleccionado"

        }


    }
}
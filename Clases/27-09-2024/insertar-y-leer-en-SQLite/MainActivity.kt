package com.example.sqllite1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var bdHelper: BaseDedatosHelper
    lateinit var etNombre: EditText
    lateinit var etEmail: EditText
    lateinit var btnAgregar: Button
    lateinit var lvUsuarios: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        bdHelper = BaseDedatosHelper(this)
        etNombre = findViewById(R.id.etNombre)
        etEmail = findViewById(R.id.etEmail)
        btnAgregar = findViewById(R.id.btnAgregar)
        lvUsuarios = findViewById(R.id.lvUsuarios)

        btnAgregar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()

            if(nombre.isNotEmpty() && email.isNotEmpty()){
                bdHelper.addUser(nombre, email)
                etNombre.text.clear()
                etEmail.text.clear()
                cargarUsuarios()
            }
        }
        cargarUsuarios()
    }
    private fun cargarUsuarios(){
        val cursor = bdHelper.obtenerUsuarios()
       val listaUsuarios = mutableListOf<String>()
        cursor?.let {
            while (cursor.moveToNext()){
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(BaseDedatosHelper.COLUMN_NAME))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(BaseDedatosHelper.COLUMN_EMAIL))
                listaUsuarios.add("$nombre - $email")
            }
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUsuarios)
        lvUsuarios.adapter = adapter
    }
}
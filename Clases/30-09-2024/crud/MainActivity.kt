package com.example.sqlitecurd

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var db: DatabaseHelper
    lateinit var editTextId: EditText
    lateinit var editTextNombre: EditText
    lateinit var editTextApellido: EditText
    lateinit var editTextEmail: EditText
    lateinit var editTextTelefono: EditText
    lateinit var btnAgregar: Button
    lateinit var btnListar: Button
    lateinit var btnActualizar: Button
    lateinit var btnEliminar: Button
    lateinit var btnCargarDatos: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)
        editTextId = findViewById(R.id.editTextId)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextApellido = findViewById(R.id.editTextApellido)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnListar = findViewById(R.id.btnListar)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnCargarDatos = findViewById(R.id.btnCargarDatos)

        agregarUsuario()
        listar()
       
    }
    private fun showMessage(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    private fun limpiarEditText() {
        editTextId.text.clear()
        editTextNombre.text.clear()
        editTextApellido.text.clear()
        editTextEmail.text.clear()
        editTextTelefono.text.clear()
    }
    private fun validarEditText(): Boolean {
        if (editTextNombre.text.toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el nombre", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextApellido.text.toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el apellido", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el email", Toast.LENGTH_LONG).show()
            return false
        }
        if (editTextTelefono.text.toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el teléfono", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun agregarUsuario() {
        btnAgregar.setOnClickListener {
            if (!validarEditText()) {
                return@setOnClickListener  // Si los campos están vacíos, detener la operación
            }
            val insertar = db.insertarUsuario(
                editTextNombre.text.toString(),
                editTextApellido.text.toString(),
                editTextEmail.text.toString(),
                editTextTelefono.text.toString()
            )
            if (insertar) {
                Toast.makeText(this, "Usuario Agregado", Toast.LENGTH_LONG).show()
                limpiarEditText()
            }else {
                Toast.makeText(this, "Usuario no agregado", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun listar() {
        btnListar.setOnClickListener {
            val res: Cursor = db.obtenerUsuarios()
            if (res.count == 0) {
                showMessage("Error", "Sin registros")
                return@setOnClickListener
            }

            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("Id :" + res.getString(0) + "\n")
                buffer.append("Nombre :" + res.getString(1) + "\n")
                buffer.append("Apellido :" + res.getString(2) + "\n")
                buffer.append("Email :" + res.getString(3) + "\n")
                buffer.append("Telefono :" + res.getString(4) + "\n\n")
            }

            showMessage("Data", buffer.toString())
        }
    }

 


   

}
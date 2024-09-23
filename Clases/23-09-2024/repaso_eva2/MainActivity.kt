package cl.dhcp.repasopruebadosejdos

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnGuardar = findViewById<Button>(R.id.btGuardar)

        btnGuardar.setOnClickListener{
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val edad = etEdad.text.toString()
            val email = etEmail.text.toString()
        if (nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
            //} else if (!isValidEmail(email)) {
            //                Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show()
            //            }
        }else{
            val intent = Intent(this, MostrarRegistro::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("apellido", apellido)
                putExtra("edad", edad)
                putExtra("email", email)
            }
            startActivity(intent)
        }

            }
    }
  /*  private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }*/
}
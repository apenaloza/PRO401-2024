package cl.dhcp.actividaduno

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val peso = findViewById<EditText>(R.id.etPeso)
        val estatura = findViewById<EditText>(R.id.etEstatura)
        val resultado = findViewById<TextView>(R.id.txtResultado)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener{
            val pesoNum = peso.text.toString().toInt()
            val estaturaNum = estatura.text.toString().toDouble()
            val imc = pesoNum / (estaturaNum * estaturaNum)
            resultado.text = "IMC: $imc"
        }
    }
}
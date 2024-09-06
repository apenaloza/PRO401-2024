package cl.dhcp.rbuttonejemplo

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
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

        val radioGroup = findViewById<RadioGroup>(R.id.rgColor)
        val btnColor = findViewById<Button>(R.id.btnColor)
        val txtColor = findViewById<TextView>(R.id.txtColor)

        btnColor.setOnClickListener{
            val opcionSeleccionada = radioGroup.checkedRadioButtonId
            val color = when(opcionSeleccionada){
                R.id.rb_rojo -> "Rojo"
                R.id.rb_verde -> "Verde"
                R.id.rb_Azul -> "Azul"
                else -> "No seleccionado"

            }
            txtColor.text = "El color es: $color"


        }

    }
}
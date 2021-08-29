package view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import edu.neo.censoappv2.R

class MainActivity : AppCompatActivity() {
    lateinit var btn_ing: Button
    lateinit var btn_mod_borrar: Button
    lateinit var btn_consultas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //La idea inicial era usar fragmentos, usar un navigation bottom view
        //y manejar los componentes de cada fragmento a traves de viewBinding
        //pero por cuestiones de tiempo, tuve que ir a lo seguro y seguir usando
        //actividades + material design.

        btn_ing = findViewById(R.id.btn_ing_main)
        btn_consultas = findViewById(R.id.btn_consulta_main)
        btn_mod_borrar = findViewById(R.id.btn_mod_borrar)
        btn_ing.setOnClickListener {
            startActivity(Intent(this, IngresarActivity::class.java))
        }
        btn_consultas.setOnClickListener {
            startActivity(Intent(this, ConsultarActivity::class.java))
        }
        btn_mod_borrar.setOnClickListener {
            startActivity(Intent(this, ModBorrarActivity::class.java))
        }
    }


}
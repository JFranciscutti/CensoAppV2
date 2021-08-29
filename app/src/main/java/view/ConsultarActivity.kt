package view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.censoappv2.R
import view.recyclerview.ConsultaAdapter
import viewmodel.ConsultarViewModel

class ConsultarActivity : AppCompatActivity() {
    lateinit var spinnerConsultas: Spinner
    lateinit var rv_consultas: RecyclerView
    lateinit var cantPersonasText: TextView
    val cvm = ConsultarViewModel()
    val consultas = arrayOf(
        "Todas las personas (A-Z)",
        "Personas +18 desocupadas",
        "Personas por debajo del indice de pobreza",
        "Cantidad de hombres",
        "Cantidad de mujeres"
    )

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)
        rv_consultas = findViewById(R.id.rv_consultar)
        spinnerConsultas = findViewById(R.id.spinner_consultas)
        cantPersonasText = findViewById(R.id.cant_personas_text)
        var adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, consultas)
        spinnerConsultas.adapter = adaptador

        rv_consultas.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_consultas.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        spinnerConsultas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var sp_adapter: ConsultaAdapter
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    consultas[0] -> {
                        sp_adapter = ConsultaAdapter(cvm.personasAtoZ(applicationContext))
                        rv_consultas.adapter = sp_adapter
                        cantPersonasText.text = ""
                    }
                    consultas[1] -> {
                        sp_adapter = ConsultaAdapter(cvm.desocupadosMayores(applicationContext))
                        rv_consultas.adapter = sp_adapter
                        cantPersonasText.text = ""
                    }
                    consultas[2] -> {
                        sp_adapter = ConsultaAdapter(cvm.pobres(applicationContext))
                        rv_consultas.adapter = sp_adapter
                        cantPersonasText.text = ""
                    }
                    consultas[3] -> {
                        cantPersonasText.text =
                            "Cantidad de hombres: " + cvm.cantHombres(applicationContext)
                        rv_consultas.adapter = null
                    }
                    consultas[4] -> {
                        cantPersonasText.text =
                            "Cantidad de mujeres: " + cvm.cantMujeres(applicationContext)
                        rv_consultas.adapter = null
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }
}
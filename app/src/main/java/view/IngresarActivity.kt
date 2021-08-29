package view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.neo.censoappv2.R
import viewmodel.IngresarViewModel
import java.text.SimpleDateFormat
import java.util.*

class IngresarActivity : AppCompatActivity() {

    private lateinit var spinnerTipoDoc: Spinner
    private lateinit var spinnerOcupacion: Spinner
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var nroDoc: EditText
    private lateinit var fechaNacimiento: EditText
    private lateinit var rgSexo: RadioGroup
    private lateinit var direccion: EditText
    private lateinit var telefono: EditText
    private lateinit var ingresos: EditText
    private lateinit var btnIngresar: Button

    val tiposDoc = arrayOf("DNI", "LC", "LE", "CI")
    val ocupaciones = arrayOf(
        "Empresario/a",
        "Docente",
        "Policia",
        "Bombero/a",
        "Médico/a",
        "Empleado/a de comercio",
        "Desocupado/a",
        "Otro"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)

        val ivm = ViewModelProvider(this).get(IngresarViewModel::class.java)

        initComponents()
        initSpinners()

        var tipoSeleccionado = ""
        spinnerTipoDoc.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("najafji")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    tipoSeleccionado = tiposDoc[position]
                }
            }

        var ocupacion_seleccionada = ""
        spinnerOcupacion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    ocupacion_seleccionada = ocupaciones[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


        btnIngresar.setOnClickListener {
            if (checkFields(
                    nombre.text.toString(),
                    apellido.text.toString(),
                    tipoSeleccionado,
                    nroDoc.text.toString(),
                    fechaNacimiento.text.toString(),
                    ingresos.text.toString(),
                    ocupacion_seleccionada
                )
            ) {
                val selected = rgSexo.checkedRadioButtonId

                val rb_selected: RadioButton = findViewById(selected)

                val sexoSeleccionado = rb_selected.text.toString()
                val edad = calcularEdad(fechaNacimiento.text.toString())

                val checkCarga = ivm.cargarPersona(
                    nombre.text.toString(),
                    apellido.text.toString(),
                    tipoSeleccionado,
                    nroDoc.text.toString().toInt(),
                    fechaNacimiento.text.toString(),
                    edad,
                    sexoSeleccionado,
                    direccion.text.toString(),
                    telefono.text.toString(),
                    ocupacion_seleccionada,
                    ingresos.text.toString().toInt(), this
                )

                if (checkCarga) {
                    Toast.makeText(
                        it.context,
                        "Se cargó correctamente la persona",
                        Toast.LENGTH_LONG
                    ).show()
                    clearFields()
                } else
                    Toast.makeText(it.context, "No cargó", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(it.context, "Complete los campos obligatorios", Toast.LENGTH_LONG)
                    .show()
            }
        }

        fechaNacimiento.setOnClickListener {
            showDatePickerDialog()
        }


    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "DatePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        val m = month + 1
        fechaNacimiento.setText("$day/$m/$year")
    }


    private fun initSpinners() {
        val adaptador_doc = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposDoc)
        spinnerTipoDoc.adapter = adaptador_doc
        val adaptador_ocup = ArrayAdapter(this, android.R.layout.simple_spinner_item, ocupaciones)
        spinnerOcupacion.adapter = adaptador_ocup
    }

    private fun initComponents() {
        nombre = findViewById(R.id.nombre)
        apellido = findViewById(R.id.apellido)
        spinnerTipoDoc = findViewById(R.id.spinner_tipo_doc)
        spinnerOcupacion = findViewById(R.id.spinner_ocupacion)
        nroDoc = findViewById(R.id.nro_doc)
        fechaNacimiento = findViewById(R.id.fecha_nac)
        rgSexo = findViewById(R.id.radio_sexo)
        direccion = findViewById(R.id.direccion)
        telefono = findViewById(R.id.telefono)
        ingresos = findViewById(R.id.ingresos)
        btnIngresar = findViewById(R.id.boton_ingresar)
    }

    //chequea que los campos obligatorios estén llenos
    private fun checkFields(
        nombre: String,
        apellido: String,
        tipo: String,
        nro_doc: String,
        fechaNac: String,
        ingresos: String,
        ocupacion: String,
    ): Boolean {
        return !(nombre.equals("") || apellido.equals("") || tipo.equals("") || nro_doc.equals("") || fechaNac.equals(
            ""
        ) || ingresos.equals("") || ocupacion.equals(""))
    }

    private fun clearFields() {
        nombre.text.clear()
        apellido.text.clear()
        nroDoc.text.clear()
        fechaNacimiento.text.clear()
        direccion.text.clear()
        telefono.text.clear()
        ingresos.text.clear()
    }

    @SuppressLint("SimpleDateFormat")
    fun calcularEdad(fechaNac: String): Int {
        //Es una chanchada, pero funciona un lujo
        val fecha_date: Date = SimpleDateFormat("dd/MM/yyyy").parse(fechaNac)
        val fecha_actual = Date(System.currentTimeMillis())
        val dif_fechas = fecha_actual.time - fecha_date.time
        val segundos = dif_fechas / 1000
        val minutos = segundos / 60
        val horas = minutos / 60
        val dias = horas / 24
        val anios = dias / 365
        return anios.toInt()
    }

}
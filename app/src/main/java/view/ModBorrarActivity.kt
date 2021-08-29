package view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.censoappv2.R
import view.recyclerview.ModBorrarAdapter
import viewmodel.ModBorrarViewModel

class ModBorrarActivity : AppCompatActivity() {
    lateinit var rv_mod_borrar: RecyclerView
    lateinit var modVM: ModBorrarViewModel

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mod_borrar)
        modVM = ViewModelProvider(this).get(ModBorrarViewModel::class.java)
        rv_mod_borrar = findViewById(R.id.rv_modificar)
        rv_mod_borrar.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_mod_borrar.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val listaPersonas = modVM.getAllPersonas(this)
        if (listaPersonas.isEmpty()) {
            Toast.makeText(this, "No hay gente", Toast.LENGTH_LONG).show()
        } else {
            val adaptador = ModBorrarAdapter(listaPersonas)
            rv_mod_borrar.adapter = adaptador
        }


    }
}
package viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dao.DBHelper
import model.Persona

class ModBorrarViewModel : ViewModel() {

    fun eliminarPersona(dni: Int, context: Context): Boolean {
        val db = DBHelper(context, null)
        return db.eliminarPersona(dni)
    }

    fun getAllPersonas(context: Context): ArrayList<Persona> {
        val db = DBHelper(context, null)
        return db.listaPersonas()
    }
}
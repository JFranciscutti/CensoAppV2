package viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dao.DBHelper
import model.Persona

class ConsultarViewModel : ViewModel() {

    fun personasAtoZ(context: Context): ArrayList<Persona> {
        val db = DBHelper(context, null)
        return db.listPersonasApellidosASC()
    }

    fun desocupadosMayores(context: Context): ArrayList<Persona> {
        val db = DBHelper(context, null)
        return db.listaDesocupados()
    }

    fun pobres(context: Context): ArrayList<Persona> {
        val db = DBHelper(context, null)
        return db.listaPobres()
    }

    fun cantHombres(context: Context): Int {
        val db = DBHelper(context, null)
        return db.listaHombres().size
    }

    fun cantMujeres(context: Context): Int {
        val db = DBHelper(context, null)
        return db.listaMujeres().size
    }
}
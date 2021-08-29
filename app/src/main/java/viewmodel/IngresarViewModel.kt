package viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dao.DBHelper
import model.Persona

class IngresarViewModel : ViewModel() {
    fun cargarPersona(
        nombre: String,
        apellido: String,
        tipo_doc: String,
        nro_doc: Int,
        fechaNac: String,
        edad: Int,
        sexo: String,
        dir: String,
        telefono: String,
        ocupacion: String,
        ingresos: Int,
        context: Context
    ): Boolean {
        val db = DBHelper(context, null)

        return db.cargarPersona(
            Persona(
                nombre,
                apellido,
                tipo_doc,
                nro_doc,
                fechaNac,
                edad,
                sexo,
                dir,
                telefono,
                ocupacion,
                ingresos
            )
        )
    }
}
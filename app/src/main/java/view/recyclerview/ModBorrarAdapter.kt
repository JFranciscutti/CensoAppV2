package view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.*
import androidx.recyclerview.widget.RecyclerView
import edu.neo.censoappv2.R
import model.Persona
import viewmodel.ModBorrarViewModel

class ModBorrarAdapter(val lista: ArrayList<Persona>) :
    RecyclerView.Adapter<ModBorrarAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModBorrarAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.modborrar_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModBorrarAdapter.ViewHolder, position: Int) {
        holder.nombre.text = lista[position].nombre
        holder.apellido.text = lista[position].apellido
        holder.tipo_doc.text = lista[position].tipo_doc
        holder.nro_doc.text = lista[position].nro_doc.toString()
        val mvm = ModBorrarViewModel()
        holder.btn_borrar.setOnClickListener {
            if (mvm.eliminarPersona(lista[position].nro_doc, it.context))
                Toast.makeText(
                    it.context,
                    "Se eliminó correctamente. Refrescar para ver los cambios",
                    Toast.LENGTH_LONG
                ).show()
            else
                Toast.makeText(it.context, "No se eliminó la persona", Toast.LENGTH_LONG).show()

        }

        holder.btn_mod.setOnClickListener {
            Toast.makeText(
                it.context,
                "No alcancé a terminar esta funcionalidad.",
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var apellido: TextView
        var tipo_doc: TextView
        var nro_doc: TextView
        var btn_mod: Button
        var btn_borrar: Button

        init {
            nombre = view.findViewById(R.id.m_nombre)
            apellido = view.findViewById(R.id.m_apellido)
            tipo_doc = view.findViewById(R.id.m_tipo_doc)
            nro_doc = view.findViewById(R.id.m_nro_doc)
            btn_mod = view.findViewById(R.id.btn_modificar)
            btn_borrar = view.findViewById(R.id.btn_eliminar)
        }
    }


}
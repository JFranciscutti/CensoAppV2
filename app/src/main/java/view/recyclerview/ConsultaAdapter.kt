package view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.neo.censoappv2.R
import model.Persona

class ConsultaAdapter(val lista: ArrayList<Persona>) :
    RecyclerView.Adapter<ConsultaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.consultar_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsultaAdapter.ViewHolder, position: Int) {
        holder.nombre.text = lista[position].nombre
        holder.apellido.text = lista[position].apellido
        holder.tipo_doc.text = lista[position].tipo_doc + " "
        holder.nro_doc.text = lista[position].nro_doc.toString()
        holder.fechaNac.text = lista[position].fechaNac
        holder.sexo.text = lista[position].sexo
        holder.direccion_telefono.text =
            lista[position].direccion + " - " + lista[position].telefono
        holder.ocupacion.text = lista[position].ocupacion
        holder.ingresos.text = lista[position].ingresos.toString()
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nombre: TextView
        var apellido: TextView
        var tipo_doc: TextView
        var nro_doc: TextView
        var fechaNac: TextView
        var sexo: TextView
        var direccion_telefono: TextView
        var ocupacion: TextView
        var ingresos: TextView

        init {
            nombre = view.findViewById(R.id.c_nombre)
            apellido = view.findViewById(R.id.c_apellido)
            tipo_doc = view.findViewById(R.id.c_tipo_doc)
            nro_doc = view.findViewById(R.id.c_nro_doc)
            fechaNac = view.findViewById(R.id.c_fecha_nac)
            sexo = view.findViewById(R.id.c_sexo)
            direccion_telefono = view.findViewById(R.id.c_direccion_telefono)
            ocupacion = view.findViewById(R.id.c_ocupacion)
            ingresos = view.findViewById(R.id.c_ingresos)

        }


    }

}
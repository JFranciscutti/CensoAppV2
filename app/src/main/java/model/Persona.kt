package model

import java.io.Serializable

data class Persona(
    val nombre: String,
    val apellido: String,
    val tipo_doc: String,
    val nro_doc: Int,
    val fechaNac: String,
    val edad: Int,
    val sexo: String,
    val direccion: String,
    val telefono: String,
    val ocupacion: String,
    val ingresos: Int
) : Serializable {
}
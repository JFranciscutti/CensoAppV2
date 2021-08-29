package dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import model.Persona

class DBHelper(var context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "censo.db"
        private val DATABASE_VERSION = 1

        val TABLE_PERSONAS = "personas"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_APELLIDO = "apellido"
        val COLUMN_TIPO = "tipo_documento"
        val COLUMN_NRO = "nro_doc"
        val COLUMN_FECHA = "fecha_nacimiento"
        val COLUMN_EDAD = "edad"
        val COLUMN_SEXO = "sexo"
        val COLUMN_DIR = "direccion"
        val COLUMN_TEL = "telefono"
        val COLUMN_OCUP = "ocupacion"
        val COLUMN_ING = "ingresos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable =
            ("CREATE TABLE $TABLE_PERSONAS ($COLUMN_NOMBRE TEXT, $COLUMN_APELLIDO TEXT, $COLUMN_TIPO TEXT, $COLUMN_NRO INTEGER PRIMARY KEY, $COLUMN_FECHA TEXT, $COLUMN_EDAD INTEGER, $COLUMN_SEXO TEXT, $COLUMN_DIR TEXT, $COLUMN_TEL TEXT, $COLUMN_OCUP TEXT, $COLUMN_ING INTEGER) ")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PERSONAS")
        onCreate(db)
    }

    fun cargarPersona(persona: Persona): Boolean {
        try {
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(COLUMN_NOMBRE, persona.nombre)
            values.put(COLUMN_APELLIDO, persona.apellido)
            values.put(COLUMN_TIPO, persona.tipo_doc)
            values.put(COLUMN_NRO, persona.nro_doc)
            values.put(COLUMN_FECHA, persona.fechaNac)
            values.put(COLUMN_EDAD, persona.edad)
            values.put(COLUMN_SEXO, persona.sexo)
            values.put(COLUMN_DIR, persona.direccion)
            values.put(COLUMN_TEL, persona.telefono)
            values.put(COLUMN_OCUP, persona.ocupacion)
            values.put(COLUMN_ING, persona.ingresos)

            db?.insert(TABLE_PERSONAS, null, values)

            return true
        } catch (e: Exception) {
            Log.e("ERROR DATABASE: ", e.message.toString())
            return false
        }
    }

    fun eliminarPersona(dni: Int): Boolean {
        try {
            val db = this.writableDatabase

            db?.execSQL("DELETE FROM $TABLE_PERSONAS WHERE $COLUMN_NRO = '$dni'")
            return true
        } catch (e: Exception) {
            Log.e("ERROR DATABASE: ", e.message.toString())
            return false
        }
    }

    fun listPersonasApellidosASC(): ArrayList<Persona> {
        val query = "SELECT * FROM $TABLE_PERSONAS ORDER BY $COLUMN_APELLIDO ASC"
        return recorrerTabla(query)
    }

    fun listaDesocupados(): ArrayList<Persona> {
        val query =
            "SELECT * FROM $TABLE_PERSONAS WHERE $COLUMN_EDAD >= 18 AND $COLUMN_OCUP = 'Desocupado/a'"
        return recorrerTabla(query)
    }

    fun listaPobres(): ArrayList<Persona> {
        val query = "SELECT * FROM $TABLE_PERSONAS WHERE $COLUMN_ING < 5000"
        return recorrerTabla(query)
    }

    fun listaHombres(): ArrayList<Persona> {
        val query = "SELECT * FROM $TABLE_PERSONAS WHERE $COLUMN_SEXO = 'masculino'"
        return recorrerTabla(query)
    }

    fun listaMujeres(): ArrayList<Persona> {
        val query = "SELECT * FROM $TABLE_PERSONAS WHERE $COLUMN_SEXO = 'femenino'"
        return recorrerTabla(query)
    }

    fun listaPersonas(): ArrayList<Persona> {
        val query = "SELECT * FROM $TABLE_PERSONAS"
        return recorrerTabla(query)
    }

    private fun recorrerTabla(query: String): ArrayList<Persona> {
        val db = this.readableDatabase
        val lista = ArrayList<Persona>()
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val nro_doc = cursor.getInt(cursor.getColumnIndex(COLUMN_NRO))
                val fechaNac = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val dir = cursor.getString(cursor.getColumnIndex(COLUMN_DIR))
                val tel = cursor.getString(cursor.getColumnIndex(COLUMN_TEL))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUP))
                val ingresos = cursor.getInt(cursor.getColumnIndex(COLUMN_ING))

                lista.add(
                    Persona(
                        nombre,
                        apellido,
                        tipo,
                        nro_doc,
                        fechaNac,
                        edad,
                        sexo,
                        dir,
                        tel,
                        ocupacion,
                        ingresos
                    )
                )

            } while (cursor.moveToNext())
        }
        return lista
    }

}
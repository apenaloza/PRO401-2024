package com.example.sqllite1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDedatosHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //Crear la bd usuarios
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_EMAIL TEXT)")
        db?.execSQL(CREATE_TABLE)
    }
// Actualizar la base de datos si la versión cambia

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    // Constantes para la base de datos
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UsuariosDB"
        const val TABLE_NAME = "Usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "Nombre"
        const val COLUMN_EMAIL = "email"
    }
    // Función para agregar un usuario
    fun addUser (Nombre: String, email: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, Nombre)
        contentValues.put(COLUMN_EMAIL, email)

        // Insertar fila
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }
    // Función para leer los usuarios
    fun obtenerUsuarios(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }




}
package com.example.sqlitecurd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        const val DATABASE_NAME = "usuarios.db"
        const val TABLE_NAME = "tabla_usuarios"
        const val COLUMNA_ID = "ID"
        const val COLUMNA_NOMBRE = "NOMBRE"
        const val COLUMNA_APELLIDO = "APELLIDO"
        const val COLUMNA_EMAIL = "EMAIL"
        const val COLUMNA_TELEFONO = "TELEFONO"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, APELLIDO TEXT, EMAIL TEXT, TELEFONO TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertarUsuario(name: String, surname: String, email: String, phone: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMNA_NOMBRE, name)
        contentValues.put(COLUMNA_APELLIDO, surname)
        contentValues.put(COLUMNA_EMAIL, email)
        contentValues.put(COLUMNA_TELEFONO, phone)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun actualizarUsuario(id: String, name: String, surname: String, email: String, phone: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMNA_ID, id)
        contentValues.put(COLUMNA_NOMBRE, name)
        contentValues.put(COLUMNA_APELLIDO, surname)
        contentValues.put(COLUMNA_EMAIL, email)
        contentValues.put(COLUMNA_TELEFONO, phone)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    fun eliminarUsuario(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ?", arrayOf(id))
    }

    fun obtenerUsuarios(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}
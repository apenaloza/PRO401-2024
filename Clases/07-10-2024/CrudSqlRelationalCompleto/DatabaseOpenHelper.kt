package com.example.sqlite04102024

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "escuela.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla Alumnos
        val CREATE_ALUMNOS_TABLE = ("CREATE TABLE Alumnos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT,"
                + "apellido TEXT)")
        db?.execSQL(CREATE_ALUMNOS_TABLE)

        // Crear tabla Asignaturas
        val CREATE_ASIGNATURAS_TABLE = ("CREATE TABLE Asignaturas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT)")
        db?.execSQL(CREATE_ASIGNATURAS_TABLE)

        // Crear tabla Relación Alumnos-Asignaturas
        val CREATE_RELACION_TABLE = ("CREATE TABLE Alumnos_Asignaturas ("
                + "alumno_id INTEGER,"
                + "asignatura_id INTEGER,"
                + "FOREIGN KEY(alumno_id) REFERENCES Alumnos(id),"
                + "FOREIGN KEY(asignatura_id) REFERENCES Asignaturas(id))")
        db?.execSQL(CREATE_RELACION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Alumnos")
        db?.execSQL("DROP TABLE IF EXISTS Asignaturas")
        db?.execSQL("DROP TABLE IF EXISTS Alumnos_Asignaturas")
        onCreate(db)
    }
}
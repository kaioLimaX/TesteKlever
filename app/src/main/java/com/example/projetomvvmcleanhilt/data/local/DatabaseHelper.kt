package com.example.projetomvvmcleanhilt.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    context: Context
) : SQLiteOpenHelper(// databaseHelper herda de SqLiteOpenHelper

    context,
    "user.db",
    null,
    1//vc tem que passar o conteudo, Nome do banco de dados, CursosFactory e a versao do banco

) {
    companion object {
        const val TABELA_USUARIOS = "usuarios"
        const val FIRST_NAME = "firstName"
        const val ID_USUARIO = "id_usuario"
        const val LAST_NAME = "lastName"
        const val ADDRESS = "address"
        const val AGE = "age"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "CREATE TABLE IF NOT EXISTS $TABELA_USUARIOS(" + // linha de comando responsavel para executar no execSQL
                    "$ID_USUARIO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "$FIRST_NAME text," +
                    "$LAST_NAME text," +
                    "$ADDRESS text," +
                    "$AGE text," +
                    "$EMAIL text," +
                    "$PHONE text," +
                    "$IMAGE text" +
                    ");"
        try { // try é um comando necessario para tratamento de erros de retorno de funções
            db?.execSQL(sql) // comando para executar linha de codigo mysql
            Log.i("info_tabela", "Sucesso ao criar Tabela ")
        } catch (e: Exception) { // catch captura a excessao(erro) caso o comando execSQL retornar
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar Tabela ")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("info_db", "nova versão inserida ")
    }

}
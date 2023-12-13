package com.example.projetomvvmcleanhilt.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import com.example.projetomvvmcleanhilt.domain.models.User
import javax.inject.Inject

class IUserDAOImpl @Inject constructor(
    private val databaseHelper: DatabaseHelper
) : IUserDAO {

    private val escrita = databaseHelper.writableDatabase
    private val leitura = databaseHelper.readableDatabase


    override fun listarUsuarios(): List<User> {


        return try{
            val listaUsuarios = mutableListOf<User>()
            val sql =
                "SELECT * FROM ${DatabaseHelper.TABELA_USUARIOS}" // linha de comando SQL para selecionar todos os itens da tabela Produtos
            @SuppressLint("Recycle")
            val cursor = leitura. // usado para selecionar(ler) dados
            rawQuery(sql, null)

            val idUser = cursor.getColumnIndex(DatabaseHelper.ID_USUARIO)
            val firstName = cursor.getColumnIndex(DatabaseHelper.FIRST_NAME)
            val lastName = cursor.getColumnIndex(DatabaseHelper.LAST_NAME)
            val address = cursor.getColumnIndex(DatabaseHelper.ADDRESS)
            val age = cursor.getColumnIndex(DatabaseHelper.AGE)
            val email = cursor.getColumnIndex(DatabaseHelper.EMAIL)
            val phone = cursor.getColumnIndex(DatabaseHelper.PHONE)
            val image = cursor.getColumnIndex(DatabaseHelper.IMAGE)


            while (cursor.moveToNext()){
                val id = cursor.getInt(idUser)
                val nome = cursor.getString(firstName)
                val sobrenome = cursor.getString(lastName)
                val endereco = cursor.getString(address)
                val idade = cursor.getInt(age)
                val email = cursor.getString(email)
                val telefone = cursor.getString(phone)
                val imagem = cursor.getString(image)

                listaUsuarios.add(
                    User(
                        id,
                        nome,
                        sobrenome,
                        endereco,
                        idade,
                        email,
                        telefone,
                        imagem
                        )
                )
            }





            return listaUsuarios
        }catch (e : Exception){
            emptyList()
        }
    }

    override fun adicionarUsuario(usuario: User): Boolean {
        val valores = ContentValues()
        valores.put(DatabaseHelper.FIRST_NAME, usuario.firstName)
        valores.put(DatabaseHelper.LAST_NAME, usuario.lastName)
        valores.put(DatabaseHelper.ADDRESS, usuario.address.toString())
        valores.put(DatabaseHelper.AGE, usuario.age)
        valores.put(DatabaseHelper.EMAIL, usuario.email)
        valores.put(DatabaseHelper.PHONE, usuario.phone)
        valores.put(DatabaseHelper.IMAGE, usuario.image)

        return try {
            //escrita.execSQL(sql)//whitableDatabase é utilizado para comandos de INSERT,UPDATE,DELETE
            escrita.insert(
                DatabaseHelper.TABELA_USUARIOS,
                null,
                valores
            )

            Log.i("info_db", "${DatabaseHelper.TABELA_USUARIOS} ")

            Log.i("info_db", "Sucesso ao Inserir item na Tabela ") // log do sistema
            true
        } catch (e: Exception) {
            Log.i("info_db", "Falha ao inserir item na Tabela ")

            false
        }

    }

    override fun removerUsuario(id: Int): Boolean {

        val args = arrayOf(id.toString())

        return try{
            escrita.delete(
                DatabaseHelper.TABELA_USUARIOS,
                "${DatabaseHelper.ID_USUARIO} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao Remover Item Tabela ") // log do sistema
            true

        }catch (e : Exception){
            e.printStackTrace()
            Log.i("info_db", "Falha ao Remover Tabela ")

            false
        }
    }
}



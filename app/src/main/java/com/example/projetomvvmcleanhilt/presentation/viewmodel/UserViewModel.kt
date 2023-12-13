package com.example.projetomvvmcleanhilt.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase
) : ViewModel(){

    //Privado
    private val _users = MutableLiveData<List<User>>()

    //LiveData public
    val users : LiveData<List<User>>
        get() = _users


    private val _listaFavoritos = MutableLiveData<List<User>>()

    val listaFavoritos : LiveData<List<User>>
        get() = _listaFavoritos

    private val _removerUsuario = MutableLiveData<Boolean>()

    val removerUsuario : LiveData<Boolean>
        get() = _removerUsuario

    private val _StateAddUser = MutableLiveData<Boolean>()

    //LiveData public
    val StateAddUser : LiveData<Boolean>
        get() = _StateAddUser

    init {
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            val listUser = userUseCase.recuperarListaAPI()
            _users.postValue(listUser)
        }
    }

    fun adicionarUsuario(usuario : User){
        viewModelScope.launch {
            val result = userUseCase.adicionarUsuario(usuario)
            when(result){
                true -> _StateAddUser.postValue(true)
                false -> _StateAddUser.postValue(false)
            }

        }
    }

    fun listarFavoritos(){
        viewModelScope.launch {
            val listaFavoritos = userUseCase.listarFavoritos()
            _listaFavoritos.postValue(listaFavoritos)
            Log.i("info_favoritos", "listarFavoritosViewModel: $listaFavoritos ")

            }

        }
    fun removerUsuario(id : Int){
        viewModelScope.launch {
            val result = userUseCase.removerUsuario(id)

            when(result){
                true -> _removerUsuario.postValue(true)
                false -> _removerUsuario.postValue(false)
            }


        }

    }
    }





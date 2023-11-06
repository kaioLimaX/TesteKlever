package com.example.projetomvvmcleanhilt.presentation.viewmodel

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

    init {
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            val listUser = userUseCase()
            _users.postValue(listUser)
        }
    }





}
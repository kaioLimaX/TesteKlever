package com.example.projetomvvmcleanhilt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projetomvvmcleanhilt.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase
) : ViewModel(){


}
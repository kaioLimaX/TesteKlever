package com.example.projetomvvmcleanhilt.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projetomvvmcleanhilt.R
import com.example.projetomvvmcleanhilt.databinding.ActivityMainBinding
import com.example.projetomvvmcleanhilt.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

//DTO - data transfer object(model de transferencia da api)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val userViewModel: UserViewModel by viewModels()
    // private val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //   userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.users.observe(this) { listUsers ->
            var listResult = ""
            listUsers.forEach { user ->
                listResult += "+) ${user.firstName},${user.lastName}  - age: ${user.age} \n"
            }

            binding.txtResult.text = listResult
        }
    }
}
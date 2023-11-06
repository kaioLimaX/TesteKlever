package com.example.projetomvvmcleanhilt.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetomvvmcleanhilt.R
import com.example.projetomvvmcleanhilt.adapter.UserAdapter
import com.example.projetomvvmcleanhilt.databinding.ActivityMainBinding
import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//DTO - data transfer object(model de transferencia da api)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var userAdapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels()
    // private val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = userAdapter

        //   userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.users.observe(this) { listUsers ->
            userAdapter.updateData(listUsers)

          //  binding.txtResult.text = listResult
        }
    }
}
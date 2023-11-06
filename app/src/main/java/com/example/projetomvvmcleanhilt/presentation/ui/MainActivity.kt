package com.example.projetomvvmcleanhilt.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetomvvmcleanhilt.R
import dagger.hilt.android.AndroidEntryPoint
//DTO - data transfer object(model de transferencia da api)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
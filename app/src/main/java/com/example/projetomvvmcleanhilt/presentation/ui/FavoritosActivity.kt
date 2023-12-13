package com.example.projetomvvmcleanhilt.presentation.ui

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetomvvmcleanhilt.R
import com.example.projetomvvmcleanhilt.adapter.OnItemClickListener
import com.example.projetomvvmcleanhilt.adapter.UserAdapter
import com.example.projetomvvmcleanhilt.databinding.ActivityFavoritosBinding
import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.presentation.viewmodel.UserViewModel
import com.example.projetomvvmcleanhilt.utils.customDialog.CustomAlertDialogFragment
import com.example.projetomvvmcleanhilt.utils.customDialog.CustomAlertDialogListener
import com.example.projetomvvmcleanhilt.utils.swipeExcluir.SwipeActionListener
import com.example.projetomvvmcleanhilt.utils.swipeExcluir.SwipeCallback
import com.skydevices.projetomvvmcleanhilt.Utils.dialogUtil.DialogData
import com.skydevices.projetomvvmcleanhilt.Utils.dialogUtil.RoundedAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritosActivity : AppCompatActivity(), CustomAlertDialogListener, SwipeActionListener {

    val binding by lazy {
        ActivityFavoritosBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var userAdapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = binding.include.toolbar  // Acesse a Toolbar através da inclusão
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Favoritos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        userViewModel.listarFavoritos()

        configurarswipe()

        userViewModel.listaFavoritos.observe(this){
            userAdapter.updateData(it)
        }

        userViewModel.removerUsuario.observe(this){
            when(it){
                true -> Toast.makeText(this, "Usuario Removido", Toast.LENGTH_SHORT).show()
                false -> Toast.makeText(this, "falha ao remover Usuario", Toast.LENGTH_SHORT).show()
            }
        }

        userAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(user: User) {
                showCustomAlertDialog(user)
            }



            override fun onItemLongClick(user: User): Boolean {
                Toast.makeText(applicationContext, user.firstName, Toast.LENGTH_SHORT).show()
                return true
            }


        })


        binding.rvFavoritos.layoutManager = LinearLayoutManager(this)
        binding.rvFavoritos.adapter = userAdapter
    }

    private fun configurarswipe() {
        val swipeCallback = SwipeCallback(this)

        // Anexe o SwipeCallback ao ItemTouchHelper
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvFavoritos)
    }

    private fun showCustomAlertDialog(someObject: User) {
        // Substitua YourObjectType pelo tipo real do seu objeto
        val alertDialog = CustomAlertDialogFragment(someObject, this)
        alertDialog.show(supportFragmentManager, "custom_dialog_tag")
    }

    override fun onPositiveClick() {
        // Lógica quando o botão positivo é clicado
    }

    override fun onNegativeClick() {
        // Lógica quando o botão negativo é clicado
    }

    override fun onSwipeLeft(pos: Int) {
        val usuario = userAdapter.getItemAtPosition(pos)
        userViewModel.removerUsuario(usuario!!.id)
        userAdapter.removerItem(pos)
    }
}


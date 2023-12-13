package com.example.projetomvvmcleanhilt.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetomvvmcleanhilt.R
import com.example.projetomvvmcleanhilt.adapter.OnItemClickListener
import com.example.projetomvvmcleanhilt.adapter.UserAdapter
import com.example.projetomvvmcleanhilt.databinding.ActivityMainBinding
import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.presentation.viewmodel.UserViewModel
import com.skydevices.projetomvvmcleanhilt.Utils.dialogUtil.DialogData
import com.skydevices.projetomvvmcleanhilt.Utils.dialogUtil.RoundedAlertDialog
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

        val toolbar = binding.include.toolbar  // Acesse a Toolbar através da inclusão
        setSupportActionBar(toolbar)




        userAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(user: User) {
              //  userViewModel.adicionarUsuario(user)
            }

            override fun onItemLongClick(user: User): Boolean {
                val dialogFragment = RoundedAlertDialog(
                    DialogData.dialogFavoritos.title,
                    "deseja adicionar o usuario ${user.firstName} aos favoritos",
                    DialogData.dialogFavoritos.buttonText,
                    DialogData.dialogFavoritos.iconResId,
                    {//onPositive
                        userViewModel.adicionarUsuario(user)

                    },
                    {//onNegative

                    }
                )
                dialogFragment.show(supportFragmentManager, "ExibirFinalizarDialog")
                return true
            }


        })


        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = userAdapter

        //   userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.listaFavoritos.observe(this){
            it.forEach {
                Log.i("info_favoritos", "onCreate: ${it.firstName} ")
            }
        }

        userViewModel.StateAddUser.observe(this){
            when(it){
                true -> Toast.makeText(this, "Sucesso ao adicionar ao favoritos", Toast.LENGTH_SHORT).show()
                false -> Toast.makeText(this, "Falha ao adicionar favoritos", Toast.LENGTH_SHORT).show()
            }
        }
        userViewModel.users.observe(this) { listUsers ->
            userAdapter.updateData(listUsers)

          //  binding.txtResult.text = listResult
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                // Lógica para manipular o clique no item de menu "Favoritos"
                val intent = Intent(this, FavoritosActivity::class.java)
                startActivity(intent)
                return true
            }
            // Adicione casos para outros itens de menu conforme necessário
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
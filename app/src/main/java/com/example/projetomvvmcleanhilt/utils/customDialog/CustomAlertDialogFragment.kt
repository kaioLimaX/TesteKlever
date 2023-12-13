package com.example.projetomvvmcleanhilt.utils.customDialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.projetomvvmcleanhilt.R
import com.example.projetomvvmcleanhilt.databinding.CustomAlertDialogBinding
import com.example.projetomvvmcleanhilt.domain.models.User
import com.squareup.picasso.Picasso


class CustomAlertDialogFragment(
    private val user: User,  // Substitua YourObjectType pelo tipo do seu objeto
    private val listener: CustomAlertDialogListener
) : DialogFragment() {

    private lateinit var binding: CustomAlertDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomAlertDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar a interface do AlertDialog conforme necessário
        Picasso.get()
            .load(user.image)
            .placeholder(R.drawable.carregando)
            .into(binding.imgUser)

        binding.txtUser.setText(
            "Nome: ${user.firstName} ${user.lastName}\n" +
            "Idade: ${user.age} \n" +
            "email: ${user.email} \n" +
            "Telefone: ${user.phone} \n" +
            "endereço: ${user.address} "

        )

        binding.button.setOnClickListener {
            listener.onPositiveClick()
            dismiss()
        }


    }
}
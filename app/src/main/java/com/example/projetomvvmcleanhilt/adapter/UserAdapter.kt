package com.example.projetomvvmcleanhilt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetomvvmcleanhilt.databinding.ItemUserBinding
import com.example.projetomvvmcleanhilt.domain.models.User
import com.facebook.shimmer.ShimmerFrameLayout
import javax.inject.Inject


class UserAdapter @Inject constructor() :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    var showShimmer = true;

    var items: MutableList<User> = mutableListOf()

    fun updateData(newData: List<User>) {
        showShimmer = false
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val shimmerLayout: ShimmerFrameLayout = binding.shimmerLayout

        fun bind(item: User) {

            binding.txtTituloPostagem.text = "${item.firstName} ${item.lastName} "
            binding.txtDescription.text = item.address.toString()
            binding.txtDescription.background = null
            binding.txtIdUsuario.background = null
            binding.imageView.background = null


        }

        fun showShimmer(show: Boolean) {
            if (show) {
                shimmerLayout.startShimmer()
                binding.contentLayout.alpha = 0.4f
            } else {
                shimmerLayout.stopShimmer()
                binding.contentLayout.alpha = 1.0f
                shimmerLayout.setShimmer(null)
                binding.txtTituloPostagem.background = null
            }
        }


    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (showShimmer) {
            holder.showShimmer(true)

        } else {

            holder.showShimmer(false)
            holder.bind(items[position])


        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 10
        return if (showShimmer) SHIMMER_ITEM_NUMBER else items.size
    }


}
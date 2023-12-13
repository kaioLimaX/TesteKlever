package com.example.projetomvvmcleanhilt.adapter

import android.annotation.SuppressLint
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetomvvmcleanhilt.databinding.ItemUserBinding
import com.example.projetomvvmcleanhilt.domain.models.User
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import javax.inject.Inject


class UserAdapter @Inject constructor() :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null
    var showShimmer = true
    var items: MutableList<User> = mutableListOf()

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun getItemAtPosition(position: Int): User? {
        return if (position in 0 until items.size) {
            items[position]
        } else {
            null
        }
    }

    fun removerItem(position: Int) {
        if (position in 0 until items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    // Atualiza os dados com efeito de shimmer
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<User>) {
        showShimmer = true
        notifyDataSetChanged()

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            showShimmer = false
            items.clear()
            items.addAll(newData)
            notifyDataSetChanged()
        }, 3000)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val user = items[position]
                listener?.onItemClick(user)
            }
        }

        viewHolder.itemView.setOnLongClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val user = items[position]
                listener?.onItemLongClick(user) ?: false
            } else {
                false
            }
        }

        return viewHolder
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

    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val shimmerLayout: ShimmerFrameLayout = binding.shimmerLayout

        fun bind(item: User) {
            binding.txtTituloPostagem.text = "Nome: ${item.firstName} ${item.lastName} "
            binding.txtDescription.text = "email: ${item.email}"
            binding.txtIdUsuario.text = "idade: ${item.age}"
            binding.txtDescription.background = null
            binding.txtTituloPostagem.background = null
            binding.txtIdUsuario.background = null
            binding.imageView.background = null
            Picasso.get()
                .load(item.image)
                .into(binding.imageView)
        }

        fun showShimmer(show: Boolean) {
            if (show) {
                shimmerLayout.startShimmer()
                binding.contentLayout.alpha = 0.4f
            } else {
                shimmerLayout.stopShimmer()
                binding.contentLayout.alpha = 1.0f
                shimmerLayout.setShimmer(null)
            }
        }
    }
}
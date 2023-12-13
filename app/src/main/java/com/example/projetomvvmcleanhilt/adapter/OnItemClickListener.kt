package com.example.projetomvvmcleanhilt.adapter

import com.example.projetomvvmcleanhilt.domain.models.User

interface OnItemClickListener {
    fun onItemClick(user: User)
    fun onItemLongClick(user: User): Boolean
}
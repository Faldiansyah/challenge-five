package com.cowok.hijrah.challenge5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cowok.hijrah.challenge5.databinding.ActivityLoginBinding
import com.cowok.hijrah.challenge5.model.GetAllUserResponseItem

class UserAdapter(var listUser: List<GetAllUserResponseItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    class ViewHolder(var binding: ActivityLoginBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ActivityLoginBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = listUser[position].id
        val name = listUser[position].name
        val username = listUser[position].username
        val pass = listUser[position].password

        var inputUser = holder.binding.editUsername.text.toString()
        var inputPass = holder.binding.editPassword.text.toString()
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}
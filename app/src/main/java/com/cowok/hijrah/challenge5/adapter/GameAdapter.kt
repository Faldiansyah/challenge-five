package com.cowok.hijrah.challenge5.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cowok.hijrah.challenge5.DetailGameActivity
import com.cowok.hijrah.challenge5.databinding.ItemGameBinding
import com.cowok.hijrah.challenge5.model.GetAllGamesResponseItem

class GameAdapter(private var listGame: List<GetAllGamesResponseItem>):
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    var onDetail : ((GetAllGamesResponseItem)->Unit)? = null

    class ViewHolder(var binding : ItemGameBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.judulGame.text = listGame[position].title
        holder.binding.tglRilisGame.text = listGame[position].time
        Glide.with(holder.itemView.context).load(listGame[position].thumb).into(holder.binding.imgGame)

        // Button Detail
        holder.binding.btnDetailGame.setOnClickListener {
            val intent = Intent(it.context, DetailGameActivity::class.java)
            intent.putExtra("detailGame", listGame[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listGame.size
    }
}
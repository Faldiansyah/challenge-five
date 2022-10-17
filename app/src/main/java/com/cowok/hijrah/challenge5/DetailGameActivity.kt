package com.cowok.hijrah.challenge5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cowok.hijrah.challenge5.databinding.ActivityDetailGameBinding
import com.cowok.hijrah.challenge5.model.GetAllGamesResponseItem

class DetailGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailGameBinding
    private lateinit var dataGame: GetAllGamesResponseItem

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataGame = intent.getSerializableExtra("detailGame") as GetAllGamesResponseItem

        val pembuat = "Pembuat Game : "
        val tanggal = "Tanggal Rilis : "
        val deskripsi = "Deskripsi :\n"

        Glide.with(this).load(dataGame.thumb).into(binding.imgGame)
        binding.titleGame.text = dataGame.title
        binding.authorGame.text = pembuat+dataGame.author
        binding.timeGame.text = tanggal+dataGame.time
        binding.descGame.text = deskripsi+dataGame.desc

    }
}
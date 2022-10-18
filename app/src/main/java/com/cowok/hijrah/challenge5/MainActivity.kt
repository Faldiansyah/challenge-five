package com.cowok.hijrah.challenge5

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cowok.hijrah.challenge5.adapter.GameAdapter
import com.cowok.hijrah.challenge5.databinding.ActivityMainBinding
import com.cowok.hijrah.challenge5.viewmodel.ViewModelGame

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gameAdapter: GameAdapter
    private lateinit var sharedPref: SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        binding.welcomeText.setText(
            "Selamat Datang, "+sharedPref.getString("name", "")
        )

        binding.imageProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        showDataGame()

    }

    override fun onResume() {
        super.onResume()
        showDataGame()
    }

    fun showDataGame(){
        val viewModel = ViewModelProvider(this).get(ViewModelGame::class.java)
        viewModel.getLiveDataGame().observe(this, Observer {

            Log.d("response", "showDataGame: " + it.toString())

            viewModel.loading.observe(this, Observer {
                when (it){
                    true -> binding.progressBarMain.visibility = View.VISIBLE
                    false -> binding.progressBarMain.visibility = View.GONE
                }
            })

            if (it != null){
                binding.rvGame.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )
                gameAdapter = GameAdapter(it)
                binding.rvGame.adapter = GameAdapter(it)
            } else {
                Toast.makeText(this, "Tidak Ada Data Yang Bisa Ditampilkan!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.callApiGame()
    }
}
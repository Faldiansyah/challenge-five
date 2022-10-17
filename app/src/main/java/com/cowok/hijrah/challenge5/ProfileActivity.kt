package com.cowok.hijrah.challenge5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cowok.hijrah.challenge5.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
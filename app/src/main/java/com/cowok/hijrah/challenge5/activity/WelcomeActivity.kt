package com.cowok.hijrah.challenge5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cowok.hijrah.challenge5.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val dbUser = sharedPref.getString("username", "")

        // periksa data user
        if (dbUser == "") {
            val dbUsername = sharedPref.getString("username", "")
            val bundle = Bundle()
            bundle.putString("username", dbUsername)

            Handler(Looper.myLooper()!!).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 3000)
        } else {
            Handler(Looper.myLooper()!!).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }

    }
}
package com.cowok.hijrah.challenge5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.cowok.hijrah.challenge5.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var dbUser = sharedPref.getString("username", "")

        // seleksi data di sharedpreferencenya ada atau tidak.
        if (dbUser == "") {
            var dbUsername = sharedPref.getString("username", "You!")
            var bundle = Bundle()
            bundle.putString("username", dbUsername)

            Handler(Looper.myLooper()!!).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 3000)
        }else{
            Handler(Looper.myLooper()!!).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }

    }
}
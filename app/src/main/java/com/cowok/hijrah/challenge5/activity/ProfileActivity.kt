package com.cowok.hijrah.challenge5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cowok.hijrah.challenge5.databinding.ActivityProfileBinding
import com.cowok.hijrah.challenge5.viewmodel.ViewModelUser

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        // ambil data dari API
        val username = sharedPref.getString("username", "")
        val name = sharedPref.getString("name", "")
        val age = sharedPref.getInt("age", 0)
        val address = sharedPref.getString("address", "")

        binding.editUsername.setText(username)
        binding.editFullname.setText(name)
        binding.editUmur.setText(age.toString())
        binding.editAlamat.setText(address)

        binding.btnLogout.setOnClickListener {
            val addUser = sharedPref.edit()
            addUser.putString("username", "")
            addUser.putString("password", "")
            addUser.apply()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnUpdate.setOnClickListener {
            val id = sharedPref.getString("id", "")
            val username = binding.editUsername.text.toString()
            val password = sharedPref.getString("password", "")
            val name = binding.editFullname.text.toString()
            val age = binding.editUmur.text.toString().toInt()
            val address = binding.editAlamat.text.toString()

            updateDataUser(id!!.toInt(), name, username, password!!, age, address)

            // update data di sharedpref
            val addUser = sharedPref.edit()
            addUser.putString("username", username)
            addUser.putString("name", name)
            addUser.putInt("age", age)
            addUser.putString("address", address)
            addUser.apply()

            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun updateDataUser(id: Int, name: String, username: String, password: String, age: Int, address: String) {
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.callEditUser(id, name, username, password, age, address)
        viewModel.editLiveDataUser().observe(this, {
            if (it != null) {
                Toast.makeText(this, "Data Berhasil Diubah!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
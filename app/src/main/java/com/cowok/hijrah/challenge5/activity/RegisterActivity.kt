package com.cowok.hijrah.challenge5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cowok.hijrah.challenge5.databinding.ActivityRegisterBinding
import com.cowok.hijrah.challenge5.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener {
            // data dari edittext
            val edtUsername = binding.editUserRegis.text.toString()
            val edtFullname = binding.editFullRegis.text.toString()
            val edtPassword = binding.editPassRegis.text.toString()
            val edtConfirm = binding.editConfRegis.text.toString()
            val edtAge = binding.editUmur.text.toString().toInt()
            val edtAddress = binding.editAlamat.text.toString()

            if (edtPassword.equals(edtConfirm)) {
                // masukkan data ke API
                addUser(edtUsername, edtFullname, edtPassword, edtAge, edtAddress)
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                toast("Password Tidak Cocok!")
            }
        }
    }

    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addUser(username: String, name: String, password: String, age: Int, address: String){
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.callPostApiCar(username, name, password, age, address)
        viewModel.addLiveDataUser().observe(this, {
            if (it != null){
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package com.cowok.hijrah.challenge5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cowok.hijrah.challenge5.databinding.ActivityRegisterBinding
import com.cowok.hijrah.challenge5.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        //seleksi pass = confpass
        binding.btnDaftar.setOnClickListener {

            //ambil inputan data dari layout
            var edtUsername = binding.editUserRegis.text.toString()
            var edtFullname = binding.editFullRegis.text.toString()
            var edtPassword = binding.editPassRegis.text.toString()
            var edtConfirm = binding.editConfRegis.text.toString()

            //seleksi password
            if (edtPassword.equals(edtConfirm)) {
                //masukin data ke API
                addUser(edtFullname, edtUsername, edtPassword)
                //back to login
                var pindah = Intent(this, LoginActivity::class.java)
                startActivity(pindah)
            } else toast("Password Tidak Cocok")
        }
    }

    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addUser(name: String, username: String, password: String){
        var viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.callPostApiCar(name, username, password)
        viewModel.addLiveDataUser().observe(this, {
            if (it != null){
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
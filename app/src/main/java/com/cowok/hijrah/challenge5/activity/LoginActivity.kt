package com.cowok.hijrah.challenge5.activity

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cowok.hijrah.challenge5.R
import com.cowok.hijrah.challenge5.databinding.ActivityLoginBinding
import com.cowok.hijrah.challenge5.model.GetAllUserResponseItem
import com.cowok.hijrah.challenge5.network.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            // data dari edittext
            val inputUser = binding.editUsername.text.toString()
            val inputPass = binding.editPassword.text.toString()

            if (inputUser != null && inputPass != null){
                requestLogin(inputUser, inputPass)
            } else if (inputUser == null && inputPass == null){
                toast("Username atau Password Kosong!")
            }
        }

        binding.belumPunyaAkun.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.textIndo.setOnClickListener {
            setLocale("id")
        }

        binding.textIngg.setOnClickListener {
            setLocale("en")
        }
    }

    private fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun requestLogin(username: String, password: String){
        RetrofitUser.instance.getAllUser()
            .enqueue(object : Callback<List<GetAllUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserResponseItem>>,
                    response: Response<List<GetAllUserResponseItem>>
                ) {
                    var data = false
                    if (response.isSuccessful){
                        if (response.body() != null){
                            val respon = response.body()
                            for (a in 0 until respon!!.size){
                                if (respon[a].username.equals(username) && respon[a].password.equals(password)){
                                    data = true

                                    // Add ke sharedPref
                                    val addUser = sharedPref.edit()
                                    addUser.putString("id", respon[a].id)
                                    addUser.putString("username", username)
                                    addUser.putString("password", password)
                                    addUser.putString("name", respon[a].name)
                                    addUser.putInt("age", respon[a].age)
                                    addUser.putString("address", respon[a].address)
                                    addUser.apply()

                                    toast("Login Berhasil!")
                                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                }
                            }

                            if (data == false) toast("Username atau Password Salah!")

                        }else{
                            toast("Tidak Ada Tindakan!")
                        }
                    }else{
                        toast("Gagal Memuat Data!")
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                    toast("Gagal Memuat Data OnFailure!")
                }

            })
    }

}
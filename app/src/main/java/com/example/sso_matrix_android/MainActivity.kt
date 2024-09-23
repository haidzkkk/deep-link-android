package com.example.sso_matrix_android

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sso_matrix_android.databinding.ActivityMainBinding
import com.example.sso_matrix_android.viewmodel.LoginViewModel
import com.example.sso_matrix_android.viewmodel.LoginViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: LoginViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        val data: Uri? = intent?.data
        val tokenLogin: String = data?.getQueryParameter("loginToken") ?: ""
        viewModel.testTokenAsync(tokenLogin)
        viewModel.mutableList.observe(this, Observer {
            binding.tvTitle.text = "user_id: \t${it.user_id}\n" +
                    "access_token: \t${it.access_token}\n" +
                    "home_server: \t${it.home_server}\n" +
                    "device_id: \t${it.device_id}\n"
        })
    }
}
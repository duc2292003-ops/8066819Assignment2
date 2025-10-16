package com.example.s8066819assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.s8066819assignment2.databinding.S8066819LoginBinding
import com.example.s8066819assignment2.model.LoginResult
import com.example.s8066819assignment2.view.DashboardActivity
import com.example.s8066819assignment2.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: S8066819LoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = S8066819LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsername.setText("minh")
        binding.etPassword.setText("8066819")

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter full information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { result ->
            Log.d("LoginActivity", "LoginResult received: $result")

            when (result) {
                is LoginResult.Success -> {
                    Log.d("LoginActivity", "Login successful, keypass: ${result.keypass}")
                    navigateToDashboard(result.keypass)
                }

                is LoginResult.Error -> {
                    Log.e("LoginActivity", "Login error: ${result.message}")
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }

                is LoginResult.Loading -> {
                    Log.d("LoginActivity", "Login loading...")
                }
            }
        }
    }

    private fun navigateToDashboard(keypass: String) {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("keypass", keypass)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}

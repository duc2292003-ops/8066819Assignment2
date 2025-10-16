package com.example.s8066819assignment2.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.s8066819assignment2.adapter.EntityAdapter
import com.example.s8066819assignment2.databinding.ActivityDashboardBinding
import com.example.s8066819assignment2.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        observeViewModel()

        val keypass = intent.getStringExtra("keypass")
        if (keypass.isNullOrEmpty()) {
            showError("Invalid session. Please login again.")
            finish()
            return
        }

        viewModel.loadEntities(keypass)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "World Famous Buildings"
    }

    private fun setupRecyclerView() {
        adapter = EntityAdapter { entity ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("entity", entity)
            }
            startActivity(intent)
        }
        binding.rvEntities.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.entities.observe(this) { entities ->
            adapter.submitList(entities)
            binding.tvEntityTotal.text = "Total: ${entities.size} buildings"
        }

        viewModel.errorMessage.observe(this) { error ->
            if (!error.isNullOrEmpty()) {
                showError(error)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showError(message: String) {
        binding.tvError.apply {
            text = message
            visibility = View.VISIBLE
        }
        binding.rvEntities.visibility = View.GONE
    }
}

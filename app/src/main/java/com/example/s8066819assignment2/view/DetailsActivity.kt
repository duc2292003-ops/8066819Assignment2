package com.example.s8066819assignment2.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.s8066819assignment2.databinding.ActivityDetailsBinding
import com.example.s8066819assignment2.model.Entity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        displayEntityDetails()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun displayEntityDetails() {
        val entity = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("entity", Entity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("entity") as? Entity
        }

        entity?.let {
            supportActionBar?.title = it.name

            binding.apply {
                tvName.text = it.name
                tvArchitect.text = it.architect
                tvLocation.text = it.location
                tvStyle.text = it.style
                tvYear.text = it.yearCompleted.toString()
                tvHeight.text = "${it.height} meters"
                tvDescription.text = it.description
            }
        } ?: run {
            finish()
        }
    }
}

package com.example.squadzipexam.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.squadzipexam.R
import com.example.squadzipexam.databinding.ActivityPersonDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetails : AppCompatActivity() {
    private lateinit var binding: ActivityPersonDetailsBinding
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPersonDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val personId = intent.getIntExtra("personId", 0)

        if (personId > 0) {
            viewModel.getPersonById(personId)
        }

        viewModel.personDetails.observe(this) {
            val defaultOptions = RequestOptions().error(R.drawable.ic_launcher_background)
            Glide.with(this).setDefaultRequestOptions(defaultOptions)
                .load(it.avatar)
                .centerCrop()
                .into(binding.imageDetailIv)
            binding.nameValueTv.text = "${it.firstName} ${it.lastName}"
            binding.emailValueTv.text = "${it.email}"
        }
    }
}
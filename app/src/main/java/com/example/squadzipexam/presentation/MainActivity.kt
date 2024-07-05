package com.example.squadzipexam.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squadzipexam.data.adapter.PersonAdapter
import com.example.squadzipexam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mAdapter: PersonAdapter? = null
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.persons.observe(this) { response ->
            if (response == null || response.isEmpty()) {
                handleUiUpdates(true)
                return@observe
            }
            mAdapter = PersonAdapter(response)
            val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = linearLayoutManager
            binding.recyclerView.adapter = mAdapter
            binding.progressBar.isVisible = false
            mAdapter?.onItemClick = {
                val intent = Intent(this, PersonDetails::class.java)
                intent.putExtra("personId", it)
                startActivity(intent)
            }
        }

        // handle re-fetching of data
        binding.retryBtn.setOnClickListener {
            refresh()
        }
    }

    private fun refresh() {
        viewModel.getPersons()
        handleUiUpdates(false)
    }

    fun handleUiUpdates(onError: Boolean) {
        binding.tvError.isVisible = onError
        binding.retryBtn.isVisible = onError
        // hide loading spinner if onError is true
        binding.progressBar.isVisible = !onError
    }
}
package com.example.squadzipexam.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.squadzipexam.R
import com.example.squadzipexam.data.local.PersonEntity
import com.example.squadzipexam.databinding.ItemLayoutBinding

/**
 * Created by Ivan Esguerra on 7/4/2024.
 **/
class PersonAdapter(private val persons: List<PersonEntity>)
    :RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    var onItemClick: ((personId: Int) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(persons[position]) {
                val defaultOptions = RequestOptions().error(R.drawable.ic_launcher_background)
                Glide.with(holder.itemView.context).setDefaultRequestOptions(defaultOptions)
                    .load(avatar)
                    .centerCrop()
                    .into(binding.imageIv)
                binding.personNameTv.text = "$firstName $lastName"
                holder.itemView.setOnClickListener {
                    onItemClick?.invoke(id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder(val binding: ItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)
}
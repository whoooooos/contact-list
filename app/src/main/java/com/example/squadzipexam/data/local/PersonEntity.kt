package com.example.squadzipexam.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
@Entity
data class PersonEntity(
    @PrimaryKey
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
)

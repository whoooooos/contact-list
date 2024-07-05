package com.example.squadzipexam.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
@Dao
interface PersonDao {
    @Upsert
    fun upsertAll(persons: List<PersonEntity>)

    @Query("SELECT * FROM personentity")
    fun getPersons(): List<PersonEntity>

    @Query("SELECT * FROM personentity WHERE id = :id")
    fun getPersonById(id: Int): PersonEntity
}
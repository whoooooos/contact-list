package com.example.squadzipexam.data.repository

import com.example.squadzipexam.data.local.PersonDatabase
import com.example.squadzipexam.data.local.PersonEntity
import com.example.squadzipexam.data.mappers.toPersonEntity
import com.example.squadzipexam.data.remote.PersonApi
import javax.inject.Inject

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
class PersonRepository @Inject constructor(
    private val personApi: PersonApi, private val personDatabase: PersonDatabase
) {

    suspend fun getPersons(): List<PersonEntity>? {
        val localPersonList = personDatabase.getPersonDao().getPersons()
        // check if local data exist
        // if not fetch from api
        if(localPersonList.isEmpty()) {
            try {
                val result = personApi.getPersons()
                if(result.isSuccessful && result.body() != null){
                    val persons = result.body()!!.data
                    val personEntities = persons.map { it.toPersonEntity() }
                    personDatabase.getPersonDao().upsertAll(personEntities)
                    return personEntities
                }
                return null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return localPersonList
    }

    fun getPerson(id: Int): PersonEntity {
        return personDatabase.getPersonDao().getPersonById(id)
    }
}
package com.example.squadzipexam.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.squadzipexam.data.local.PersonEntity
import com.example.squadzipexam.data.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
@HiltViewModel
class PersonViewModel @Inject constructor(
    private val repository: PersonRepository
) : ViewModel() {

    private val _persons = MutableLiveData<List<PersonEntity>>()
    val persons: LiveData<List<PersonEntity>> = _persons
    private val _personDetails = MutableLiveData<PersonEntity>()
    val personDetails: LiveData<PersonEntity> = _personDetails

    init {
        getPersons()
    }

    fun getPersons() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPersons()
            _persons.postValue(response!!)
        }
    }

    fun getPersonById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val person = repository.getPerson(id)
            _personDetails.postValue(person)
        }
    }
}
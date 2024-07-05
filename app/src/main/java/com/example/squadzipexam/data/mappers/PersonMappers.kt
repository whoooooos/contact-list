package com.example.squadzipexam.data.mappers

import com.example.squadzipexam.data.local.PersonEntity
import com.example.squadzipexam.data.remote.PersonDto

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
fun PersonDto.Person.toPersonEntity(): PersonEntity {
    return PersonEntity(
        id = id,
        email = email,
        firstName = first_name,
        lastName = last_name,
        avatar = avatar
    )
}
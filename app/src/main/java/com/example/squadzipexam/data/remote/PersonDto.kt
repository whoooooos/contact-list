package com.example.squadzipexam.data.remote

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
data class PersonDto(
    val data: List<Person>
) {
    data class Person(
        val id: Int,
        val email: String,
        val first_name: String,
        val last_name: String,
        val avatar: String,
    )
}

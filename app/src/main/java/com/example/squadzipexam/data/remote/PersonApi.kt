package com.example.squadzipexam.data.remote

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Ivan Esguerra on 7/3/2024.
 **/
interface PersonApi {
    @GET("/api/users?per_page=10")
    suspend fun getPersons(): Response<PersonDto>

    companion object {
        const val BASE_URL = "https://reqres.in"
    }
}
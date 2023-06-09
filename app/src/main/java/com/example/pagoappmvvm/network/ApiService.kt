package com.example.pagoappmvvm.network

import com.example.pagoappmvvm.model.Contact
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getContacts(): Response<List<Contact>>
}
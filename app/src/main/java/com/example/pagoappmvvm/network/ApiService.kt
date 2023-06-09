package com.example.pagoappmvvm.network

import com.example.pagoappmvvm.model.Contact
import com.example.pagoappmvvm.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getContacts(): Response<List<Contact>>

    @GET("users/{userId}/posts")
    suspend fun getPosts(@Path("userId") userId: Int): Response<List<Post>>
}
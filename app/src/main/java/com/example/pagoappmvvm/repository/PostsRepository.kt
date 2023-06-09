package com.example.pagoappmvvm.repository

import com.example.pagoappmvvm.model.Post
import com.example.pagoappmvvm.network.ApiService
import com.example.pagoappmvvm.network.RemoteServicesHandler
import com.example.pagoappmvvm.network.Resource

class PostsRepository(
    private val apiService: ApiService,
    private val servicesHandler: RemoteServicesHandler
) {

    suspend fun getPosts(userId: Int): Resource<List<Post>> =
        servicesHandler.makeTheCallAndHandleResponse(
            serviceCall = { apiService.getPosts(userId) },
            mapSuccess = { Resource.Success(it.body(), it.code()) }
        )

}
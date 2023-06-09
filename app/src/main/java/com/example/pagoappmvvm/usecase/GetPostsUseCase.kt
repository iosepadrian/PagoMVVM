package com.example.pagoappmvvm.usecase

import com.example.pagoappmvvm.network.Resource
import com.example.pagoappmvvm.repository.PostsRepository
import com.example.pagoappmvvm.repository.SessionManager
import kotlinx.coroutines.flow.flow

class GetPostsUseCase(
    private val postsRepository: PostsRepository,
    private val sessionManager: SessionManager
) {

    suspend fun getPosts(userId: Int) = flow {
        emit(Resource.Loading())
        val response = postsRepository.getPosts(userId)
        response.data?.let { sessionManager.setPosts(userId, it) }
        emit(response)
    }

}
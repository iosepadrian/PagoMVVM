package com.example.pagoappmvvm.ui.contactdetails

import androidx.lifecycle.MutableLiveData
import com.example.pagoappmvvm.model.Post
import com.example.pagoappmvvm.repository.SessionManager
import com.example.pagoappmvvm.ui.common.BaseViewModel
import com.example.pagoappmvvm.usecase.GetPostsUseCase

class ContactDetailsViewModel(
    private val sessionManager: SessionManager,
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {
    val postsLiveData: MutableLiveData<List<Post>> by lazy {
        MutableLiveData<List<Post>>()
    }

    fun getPosts(userId: Int) {
        launchAsync({ getPostsUseCase.getPosts(userId) }, onSuccess = {
            postsLiveData.setValue(it)
        })
    }

    fun getPostsFromLocal(userId: Int): List<Post> {
        return sessionManager.getPosts(userId)
    }

}
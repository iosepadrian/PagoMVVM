package com.example.pagoappmvvm.usecase

import com.example.pagoappmvvm.network.Resource
import com.example.pagoappmvvm.repository.ContactRepository
import com.example.pagoappmvvm.repository.SessionManager
import kotlinx.coroutines.flow.flow

class GetContactsUseCase(
    private val contactsRepository: ContactRepository,
    private val sessionManager: SessionManager
) {

    suspend fun getContacts() = flow {
        emit(Resource.Loading())
        val response = contactsRepository.getContacts()
        response.data?.let { sessionManager.setContacts(it) }
        emit(response)
    }

}
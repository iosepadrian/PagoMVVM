package com.example.pagoappmvvm.repository

import com.example.pagoappmvvm.model.Contact
import com.example.pagoappmvvm.network.ApiService
import com.example.pagoappmvvm.network.RemoteServicesHandler
import com.example.pagoappmvvm.network.Resource

class ContactRepository(
    private val apiService: ApiService,
    private val servicesHandler: RemoteServicesHandler
) {

    suspend fun getContacts(): Resource<List<Contact>> =
        servicesHandler.makeTheCallAndHandleResponse(
            serviceCall = { apiService.getContacts() },
            mapSuccess = { Resource.Success(it.body(), it.code()) }
        )

}
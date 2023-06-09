package com.example.pagoappmvvm.ui.contacts

import androidx.lifecycle.MutableLiveData
import com.example.pagoappmvvm.model.Contact
import com.example.pagoappmvvm.repository.SessionManager
import com.example.pagoappmvvm.ui.common.BaseViewModel
import com.example.pagoappmvvm.usecase.GetContactsUseCase

class ContactsViewModel(
    private val sessionManager: SessionManager,
    private val getContactsUseCase: GetContactsUseCase
) : BaseViewModel() {
    val contactsListLiveData: MutableLiveData<List<Contact>> by lazy {
        MutableLiveData<List<Contact>>()
    }

    fun getContacts() {
        launchAsync({ getContactsUseCase.getContacts() }, onSuccess = {
            contactsListLiveData.setValue(it)
        })
    }

    fun getContactsFromLocal(): List<Contact> {
        return sessionManager.getContacts()
    }

}
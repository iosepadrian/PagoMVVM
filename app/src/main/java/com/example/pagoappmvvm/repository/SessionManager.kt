package com.example.pagoappmvvm.repository

import android.content.SharedPreferences
import com.example.pagoappmvvm.model.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val CONTACTS_LIST = "CONTACTS_LIST"
        private val gson = Gson()
    }
    fun setContacts(contacts: List<Contact>) {
        val serializedContactsList = gson.toJson(contacts)
        sharedPreferences.edit()
            .putString(CONTACTS_LIST, serializedContactsList)
            .apply()
    }

    fun getContacts(): List<Contact> {
        val serializedContactsList = sharedPreferences.getString(CONTACTS_LIST, "[]")
        return gson.fromJson(
            serializedContactsList,
            object : TypeToken<List<Contact>?>() {}.type
        )
    }
}
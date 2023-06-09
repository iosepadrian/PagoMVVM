package com.example.pagoappmvvm.utils

import java.lang.Exception

object ContactUtils {

    const val IMAGE_URL = "https://picsum.photos/200/200"
    const val CONTACT_ACTIVE_STATUS = "active"

    fun getInitials(name: String?): String {
        return try {
            var initials = ""
            name?.split(" ")?.forEach { initials += it.first() }
            initials
        } catch (e: Exception) {
            ""
        }
    }
}
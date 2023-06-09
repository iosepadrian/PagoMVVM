package com.example.pagoappmvvm.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("user_id") var user_id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("body") var body: String? = null
) : Serializable

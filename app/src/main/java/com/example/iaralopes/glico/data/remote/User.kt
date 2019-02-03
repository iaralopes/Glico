package com.example.iaralopes.glico.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("password")
    @Expose
    var password: String,
    @SerializedName("glucoses")
    @Expose
    var glucoses: List<Glucose>? = null
)
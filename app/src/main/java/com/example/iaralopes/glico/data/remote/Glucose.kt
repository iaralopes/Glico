package com.example.iaralopes.glico.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Glucose (
    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("date")
    @Expose
    var date: String,
    @SerializedName("category")
    @Expose
    var category: String,
    @SerializedName("value")
    @Expose
    var value: String,
    @SerializedName("userId")
    @Expose
    var userId: Long
)
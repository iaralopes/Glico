package com.example.iaralopes.glico.core.home.utils

class OnClickState<D>(
    val status: Status,
    val data: D? = null,
    val position: Int = 0
) {
    enum class Status{
        DELETE
    }
}
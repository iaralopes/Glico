package com.example.iaralopes.glico.base

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}
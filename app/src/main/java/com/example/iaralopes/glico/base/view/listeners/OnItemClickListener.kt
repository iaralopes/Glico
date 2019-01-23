package com.example.iaralopes.glico.base.view.listeners

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}
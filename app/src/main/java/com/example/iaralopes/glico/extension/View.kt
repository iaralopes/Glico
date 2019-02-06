package com.example.iaralopes.glico.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(factory: ViewModelProvider.Factory): T
        = ViewModelProviders.of(this, factory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory): T
        = ViewModelProviders.of(this, factory)[T::class.java]
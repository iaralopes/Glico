package com.example.iaralopes.glico.core.login

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.core.MainActivity
import com.example.iaralopes.glico.data.remote.User
import com.example.iaralopes.glico.databinding.ActivityLoginBinding
import com.example.iaralopes.glico.extension.viewModel
import com.example.iaralopes.glico.utils.FlowState
import org.jetbrains.anko.startActivity


class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel

        setObservableViewModel()
        startActivity<MainActivity>()
    }

    private fun setObservableViewModel() {
        loginViewModel.loginState().observe(this,
            Observer { it?.let { handleLoginState(it) } })
    }

    private fun handleLoginState(state: FlowState<User>) {
        when(state.status) {
            FlowState.Status.LOADING -> {

            }
            FlowState.Status.SUCCESS -> {
//                startActivity<HomeFragment>()
            }
            FlowState.Status.ERROR -> {

            }
        }
    }

    fun onClickLogin(view: View) {
        loginViewModel.login()
    }

}

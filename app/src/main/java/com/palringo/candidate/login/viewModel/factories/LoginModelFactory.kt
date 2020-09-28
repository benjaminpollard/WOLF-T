package com.palringo.candidate.login.viewModel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.palringo.candidate.login.repositories.EmailLoginRepo
import com.palringo.candidate.login.repositories.FacebookLoginRepo
import com.palringo.candidate.login.repositories.GoogleLoginRepo
import com.palringo.candidate.login.repositories.LoginRepo
import com.palringo.candidate.login.viewModel.LoginViewModel

class LoginModelFactory(
        private val loginRepo: LoginRepo,
        private val facebookLoginRepo: FacebookLoginRepo,
        private val emailLoginRepo: EmailLoginRepo,
        private val googleLoginRepo: GoogleLoginRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(loginRepo, facebookLoginRepo, emailLoginRepo, googleLoginRepo) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }

}
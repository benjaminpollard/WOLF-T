package com.palringo.candidate.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idea.group.iplato.repositories.models.RepositoriesListener
import com.palringo.candidate.login.models.LoginModel
import com.palringo.candidate.login.repositories.EmailLoginRepo
import com.palringo.candidate.login.repositories.FacebookLoginRepo
import com.palringo.candidate.login.repositories.GoogleLoginRepo
import com.palringo.candidate.login.repositories.LoginRepo

class LoginViewModel(private val loginRepo: LoginRepo,
                     private val facebookLoginRepo: FacebookLoginRepo,
                     private val emailLoginRepo: EmailLoginRepo,
                     private val googleLoginRepo: GoogleLoginRepo) : ViewModel() {

    private val callback: RepositoriesListener<Boolean> =
            object : RepositoriesListener<Boolean>() {
                override fun repoResult(data: Boolean?, error: String?) {
                    loggingIn = false
                    showLoading.postValue(loggingIn)

                    error?.let {
                        showError.postValue(it)
                    }
                    data?.let {
                        if (it)
                            loggedIn.postValue(Unit)
                    }
                }
            }

    init {
        loginRepo.repoCallback = callback
    }

    val loggedIn: MutableLiveData<Unit> = MutableLiveData<Unit>()
    val showLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val showError: MutableLiveData<String> = MutableLiveData<String>()

    private var loggingIn = false

    fun loginWithFacebook() {
        login(facebookLoginRepo.makeDummyObject())
    }

    fun loginWithEmail() {
        login(emailLoginRepo.makeDummyObject())
    }

    fun loginWithGoogle() {
        login(googleLoginRepo.makeDummyObject())
    }

    private fun login(model: LoginModel) {
        if (loggingIn)
            return

        loggingIn = true
        showLoading.postValue(loggingIn)
        loginRepo.requestLogin(model.makeLoginObject())
    }

}
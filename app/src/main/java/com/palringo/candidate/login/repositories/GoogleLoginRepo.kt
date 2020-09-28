package com.palringo.candidate.login.repositories

import com.palringo.candidate.login.models.GoogleLoginModel

//dummy class to reprsent calling google to get these details
class GoogleLoginRepo {
    fun makeDummyObject(): GoogleLoginModel {
        val model = GoogleLoginModel()
        model.googleToken = "sdfsdfsdfds"
        model.googleUser = "sdsfsdfvcvcvcv"
        return model
    }
}
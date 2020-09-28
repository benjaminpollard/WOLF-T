package com.palringo.candidate.login.repositories

import com.palringo.candidate.login.models.FacebookLoginModel

//dummy class to reprsent calling facebook to get these details
class FacebookLoginRepo {
    fun makeDummyObject(): FacebookLoginModel {
        val model = FacebookLoginModel()
        model.accessToken = "sdfsdfsdfds"
        model.userId = "sdsfsdfvcvcvcv"
        return model
    }
}
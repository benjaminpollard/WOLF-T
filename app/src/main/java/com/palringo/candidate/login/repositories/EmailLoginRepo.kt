package com.palringo.candidate.login.repositories

import com.palringo.candidate.login.models.EmailLoginModel

//dummy class to reprsent calling google to get these details
class EmailLoginRepo {
    fun makeDummyObject(): EmailLoginModel {
        val model = EmailLoginModel()
        model.password = "sdfsdfsdfds"
        model.username = "sdsfsdfvcvcvcv"
        return model
    }
}
package com.palringo.candidate.login.models

import org.json.JSONObject

class EmailLoginModel : LoginModel() {
    var username: String = ""
    var password: String = ""

    override fun makeLoginObject(): JSONObject {

        if (username.isBlank() || password.isBlank())
            throw IllegalArgumentException("EmailLoginModel")

        val json = JSONObject()
        json.putOpt(LoginConstants.EMAIL_USERNAME, username)
        json.putOpt(LoginConstants.EMAIL_PASSWORD, password)
        return json
    }
}
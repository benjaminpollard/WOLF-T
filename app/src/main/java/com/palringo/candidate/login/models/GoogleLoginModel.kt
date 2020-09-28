package com.palringo.candidate.login.models

import org.json.JSONObject

class GoogleLoginModel : LoginModel() {
    var googleUser: String = ""
    var googleToken: String = ""

    override fun makeLoginObject(): JSONObject {

        if (googleToken.isBlank() || googleUser.isBlank())
            throw IllegalArgumentException("GoogleLoginModel")

        val json = JSONObject()
        json.putOpt(LoginConstants.GOOGLE_USERNAME, googleUser)
        json.putOpt(LoginConstants.GOOGLE_TOKEN, googleToken)
        json.putOpt(LoginConstants.IS_GOOGLE_LOGIN, true)
        return json
    }
}
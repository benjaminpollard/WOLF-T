package com.palringo.candidate.login.models

import org.json.JSONObject

class FacebookLoginModel : LoginModel() {
    var userId: String = ""
    var accessToken: String = ""

    override fun makeLoginObject(): JSONObject {

        if (userId.isBlank() || accessToken.isBlank())
            throw IllegalArgumentException("FacebookLoginModel")

        val json = JSONObject()
        json.putOpt(LoginConstants.FACEBOOK_ID, userId)
        json.putOpt(LoginConstants.FACEBOOK_TOKEN, accessToken)
        json.putOpt(LoginConstants.IS_FACEBOOK_USER, true)

        return json
    }
}
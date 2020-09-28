package com.palringo.candidate.login.models

import org.json.JSONObject

abstract class LoginModel {
    abstract fun makeLoginObject(): JSONObject
}
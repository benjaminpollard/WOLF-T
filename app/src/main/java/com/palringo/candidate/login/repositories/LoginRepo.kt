package com.palringo.candidate.login.repositories

import com.idea.group.iplato.repositories.models.RepositoriesListener
import com.idea.group.iplato.services.LoginService
import com.palringo.candidate.login.services.Listeners.ServiceListener
import org.json.JSONObject

open class LoginRepo(private val loginService: LoginService) {

    var repoCallback: RepositoriesListener<Boolean>? = null

    fun requestLogin(request: JSONObject) {
        loginService.fakeLogin(request, object : ServiceListener<Boolean>() {
            override fun result(data: Boolean?) {
                if (data == null || data == false)
                    repoCallback?.repoResult(null, "random reason for login fail")
                else
                    repoCallback?.repoResult(true, null)
            }
        })
    }
}
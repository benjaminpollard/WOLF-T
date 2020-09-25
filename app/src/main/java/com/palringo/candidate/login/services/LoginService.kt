package com.idea.group.iplato.services


import android.os.Handler
import com.palringo.candidate.login.services.Listeners.ServiceListener
import org.json.JSONObject
import java.util.*

class LoginService {
    fun fakeLogin(requestObject: JSONObject, listener: ServiceListener<Boolean>) {
        //emulate a 5% failure to login
        if (Random().nextFloat() > 0.05f) {
            listener.result(true)
        } else {
            Handler().postDelayed(
                    { listener.result(false) }, 5000)
        }
    }
}
package com.palringo.candidate.requests

import org.json.JSONObject

val KEY_ID = "id"
val KEY_EXTENDED = "extended"
val KEY_SUBSCRIBE = "subscribe"
val GROUP_PROFILE_NAME = "group profile"

class GroupRequest(private val id: Int, private val extended: Boolean, private val subscribe: Boolean) : Request() {

    override fun getRequestBody(): JSONObject {
        val requestBody = JSONObject()
        requestBody.put(KEY_SUBSCRIBE, subscribe)
        requestBody.put(KEY_ID, id)
        requestBody.put(KEY_EXTENDED, extended)
        return requestBody
    }

    override fun hashCode(): Int {
        return id * name.hashCode() * extended.hashCode() * subscribe.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        other as GroupRequest
        return (other.extended == extended
                && other.subscribe == subscribe
                && other.id == id
                && other.name == name)
    }

    override fun getName(): String {
        return GROUP_PROFILE_NAME
    }

}
package com.palringo.candidate.login.services.Listeners

abstract class ServiceListener<T> {
    abstract fun result(data: T?)
}



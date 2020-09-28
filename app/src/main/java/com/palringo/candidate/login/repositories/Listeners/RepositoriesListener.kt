package com.idea.group.iplato.repositories.models

abstract class RepositoriesListener<T> {
    abstract fun repoResult(data: T?, error: String?)
}



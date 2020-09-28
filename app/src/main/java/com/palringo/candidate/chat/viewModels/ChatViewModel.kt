package com.palringo.candidate.chat.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {
    private val messageList = mutableListOf<String>()

    val newMessageLiveData: MutableLiveData<String> = MutableLiveData<String>()

    fun sendMessage(message: String) {
        if (message.isBlank())
            return

        messageList.add(message)
        newMessageLiveData.postValue(message)
    }

    fun getMessages(): MutableList<String> {
        //can remove last message as live data will resend last message
        //wont work with 2 people in chat
        val list = messageList.toMutableList()
        if (messageList.isNotEmpty())
            list.remove(messageList.last())
        return list
    }

}
package com.palringo.candidate.chat.models;

public class ChatItem {
    private String avatarUrl;
    private String message;

    public ChatItem(String avatarUrl, String message) {
        this.avatarUrl = avatarUrl;
        this.message = message;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getMessage() {
        return message;
    }
}

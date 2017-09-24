package com.palringo.candidate.chat;

class ChatItem {
    private String avatarUrl;
    private String message;

    ChatItem(String avatarUrl, String message) {
        this.avatarUrl = avatarUrl;
        this.message = message;
    }

    String getAvatarUrl() {
        return avatarUrl;
    }

    String getMessage() {
        return message;
    }
}

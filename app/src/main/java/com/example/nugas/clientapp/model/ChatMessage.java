package com.example.nugas.clientapp.model;

import com.google.gson.annotations.SerializedName;

public class ChatMessage {

    @SerializedName("message")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

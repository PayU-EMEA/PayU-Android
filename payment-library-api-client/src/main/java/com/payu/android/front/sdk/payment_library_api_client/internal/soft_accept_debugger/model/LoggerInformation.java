package com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.model;

import com.google.gson.annotations.SerializedName;

public class LoggerInformation {

    @SerializedName("event")
    private String event;
    @SerializedName("id")
    private String id;
    @SerializedName("level")
    private String level;
    @SerializedName("message")
    private String message;
    @SerializedName("sender")
    private String sender;

    public LoggerInformation(String event, String id, String level, String message, String sender) {
        this.event = event;
        this.id = id;
        this.level = level;
        this.message = message;
        this.sender = sender;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

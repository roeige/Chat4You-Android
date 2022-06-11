package com.example.char4you_android.entities;

import java.util.Date;

public class Message {
    int id;
    String Content;
    Date Created;
    boolean sent;

    public Message(int id, String content, Date created, boolean sent) {
        this.id = id;
        Content = content;
        Created = created;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}

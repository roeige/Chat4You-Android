package com.example.char4you_android.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Message implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String content;
    String created;
    boolean sent;
    private String fromId;
    private String toId;

    public Message(int id, String content, String created, boolean sent, String fromId, String toId) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}

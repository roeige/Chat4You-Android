package com.example.char4you_android.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String content;
    String created;
    boolean sent;

    public Message(int id, String content, String created, boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
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

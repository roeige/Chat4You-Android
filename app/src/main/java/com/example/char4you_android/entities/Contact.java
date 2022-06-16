package com.example.char4you_android.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    private String ownerId;
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastdate;

    public Contact(String ownerId, @NonNull String id, String name, String server, String last, String lastdate) {
        this.ownerId = ownerId;
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
    }

    @Ignore
    public Contact(@NonNull String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }

}

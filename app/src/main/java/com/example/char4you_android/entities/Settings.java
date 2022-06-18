package com.example.char4you_android.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {
    @PrimaryKey
    @NonNull
    private String server;

    public Settings() {
        this.server = "http://10.0.2.2:7019";
    }

    @NonNull
    public String getServer() {
        return server;
    }

    public void setServer(@NonNull String server) {
        this.server = server;
    }
}

package com.example.char4you_android.entities;

public class User {
    private String username;
    private String password;
    private String name;
    private String token;

    public User(String username, String password, String name, String token) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}

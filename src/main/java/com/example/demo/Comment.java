package com.example.demo;

public class Comment {
    String name;
    String user;
    public Comment (String name, String user) {
        this.name = name;
        this.user = user;
    }
    public Comment (String name) {
        this.name = name;
        this.user = null;
    }
    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }
}

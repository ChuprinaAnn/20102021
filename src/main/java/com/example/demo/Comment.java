package com.example.demo;

import java.util.Date;

public class Comment {
    private String name;
    private String user;
    private Date editDate;
    public Comment (String name, String user) {
        this.name = name;
        this.user = user;
    }
    public Comment (String name, String user, Date editDate) {
        this.name = name;
        this.user = user;
        this.editDate = editDate;
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
    public Date getEditDate() {
        return editDate;
    }
}

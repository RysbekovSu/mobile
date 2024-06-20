package com.example.students.model;

public class CurrentUser {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "CurrentUser{" +
                "email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public CurrentUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
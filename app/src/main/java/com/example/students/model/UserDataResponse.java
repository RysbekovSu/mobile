package com.example.students.model;

import com.google.gson.annotations.SerializedName;

public class UserDataResponse {

    public void setUsername(String username) {
        this.username = username;
    }

    @SerializedName("username")
    private String username;

    // Другие поля пользователя, которые вам нужны

    public String getUsername() {
        return username;
    }

    // Другие геттеры и сеттеры для полей
}
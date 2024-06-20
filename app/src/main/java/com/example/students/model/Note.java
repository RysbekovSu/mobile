package com.example.students.model;

public class Note {
    private String title;
    private String description;
    private String date;
    private long dateMillis;

    public Note(String title, String description, String date, long dateMillis) {
        // Initialize fields
        this.title = title;
        this.description = description;
        this.date = date;
        this.dateMillis = dateMillis;
    }

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public long getDateMillis() {
        return dateMillis;
    }

}

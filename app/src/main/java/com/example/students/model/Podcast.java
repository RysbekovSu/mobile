package com.example.students.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Podcast implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("quote")
    @Expose
    private String quote;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("text")
    @Expose
    private String text; // Новое поле типа String

    public Podcast(String name, String quote, String author, String text) {
        this.name = name;
        this.quote = quote;
        this.author = author;
        this.text = text; // Инициализация нового поля
    }

    protected Podcast(Parcel in) {
        name = in.readString();
        quote = in.readString();
        author = in.readString();
        text = in.readString(); // Чтение из Parcel
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(quote);
        dest.writeString(author);
        dest.writeString(text); // Запись в Parcel
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Podcast> CREATOR = new Creator<Podcast>() {
        @Override
        public Podcast createFromParcel(Parcel in) {
            return new Podcast(in);
        }

        @Override
        public Podcast[] newArray(int size) {
            return new Podcast[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

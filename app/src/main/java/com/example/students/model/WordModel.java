package com.example.students.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WordModel implements Parcelable {
    @SerializedName("word")
    @Expose
    private String word;

    @SerializedName("translation")
    @Expose
    private String translation;

    @SerializedName("author")
    @Expose
    private String author; // Изменили тип на String

    public WordModel(String word, String translation, String author) {
        this.word = word;
        this.translation = translation;
        this.author = author;
    }

    protected WordModel(Parcel in) {
        word = in.readString();
        translation = in.readString();
        author = in.readString(); // Изменили чтение из Parcel на String
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeString(translation);
        dest.writeString(author);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WordModel> CREATOR = new Creator<WordModel>() {
        @Override
        public WordModel createFromParcel(Parcel in) {
            return new WordModel(in);
        }

        @Override
        public WordModel[] newArray(int size) {
            return new WordModel[size];
        }
    };

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

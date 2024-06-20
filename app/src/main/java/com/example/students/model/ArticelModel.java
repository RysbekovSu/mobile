package com.example.students.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticelModel implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("quote")
    @Expose
    private String quote;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("author")
    @Expose
    private String author;

    public ArticelModel(String name, String photo, String quote, String description, String author) {
        this.name = name;
        this.photo = photo;
        this.quote = quote;
        this.description = description;
        this.author = author;
    }

    protected ArticelModel(Parcel in) {
        name = in.readString();
        photo = in.readString();
        quote = in.readString();
        description = in.readString();
        author = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(photo);
        dest.writeString(quote);
        dest.writeString(description);
        dest.writeString(author);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArticelModel> CREATOR = new Creator<ArticelModel>() {
        @Override
        public ArticelModel createFromParcel(Parcel in) {
            return new ArticelModel(in);
        }

        @Override
        public ArticelModel[] newArray(int size) {
            return new ArticelModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

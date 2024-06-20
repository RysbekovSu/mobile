package com.example.students.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apply implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("level")
    @Expose
    private String level;

    @SerializedName("rate")
    @Expose
    private int rate;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("insta")
    @Expose
    private String insta;

    @SerializedName("tel")
    @Expose
    private String tel;

    @SerializedName("num")
    @Expose
    private String num;

    @SerializedName("category")
    @Expose
    private String category; // Используем String для категории

    // Constructor
    public Apply(String name, String email, String photo, String reason, String level, int rate, int price, String description, String insta, String tel, String num, String category) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.reason = reason;
        this.level = level;
        this.rate = rate;
        this.price = price;
        this.description = description;
        this.insta = insta;
        this.tel = tel;
        this.num = num;
        this.category = category;
    }

    // Parcelable constructor
    protected Apply(Parcel in) {
        name = in.readString();
        email = in.readString();
        photo = in.readString();
        reason = in.readString();
        level = in.readString();
        rate = in.readInt();
        price = in.readInt();
        description = in.readString();
        insta = in.readString();
        tel = in.readString();
        num = in.readString();
        category = in.readString();
    }

    // Parcelable creator
    public static final Creator<Apply> CREATOR = new Creator<Apply>() {
        @Override
        public Apply createFromParcel(Parcel in) {
            return new Apply(in);
        }

        @Override
        public Apply[] newArray(int size) {
            return new Apply[size];
        }
    };

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(photo);
        dest.writeString(reason);
        dest.writeString(level);
        dest.writeInt(rate);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeString(insta);
        dest.writeString(tel);
        dest.writeString(num);
        dest.writeString(category);
    }
}

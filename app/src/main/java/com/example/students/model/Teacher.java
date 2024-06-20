package com.example.students.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("user")
    @Expose
    private int userId; // Изменили тип на int для хранения идентификатора пользователя

    @SerializedName("photo")
    @Expose
    private String photo;

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
    private String categoryId; // Изменили тип на int для хранения идентификатора категории

    public Teacher(int id, String name, String email, int userId, String photo, String level, int rate, int price, String description, String insta, String tel, String num, String categoryId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.photo = photo;
        this.level = level;
        this.rate = rate;
        this.price = price;
        this.description = description;
        this.insta = insta;
        this.tel = tel;
        this.num = num;
        this.categoryId = categoryId;
    }

    protected Teacher(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        userId = in.readInt();
        photo = in.readString();
        level = in.readString();
        rate = in.readInt();
        price = in.readInt();
        description = in.readString();
        insta = in.readString();
        tel = in.readString();
        num = in.readString();
        categoryId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeInt(userId);
        dest.writeString(photo);
        dest.writeString(level);
        dest.writeInt(rate);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeString(insta);
        dest.writeString(tel);
        dest.writeString(num);
        dest.writeString(categoryId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}

package com.hacktiv8.tokoku.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    String uId;
    String username;
    String email;
    String phone;
    //0 admin, 1 staff, 2 user
    String role;

    public User(){

    }

    public User(String uId, String username, String email, String phone, String role) {
        this.uId = uId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(uId);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(role);
    }

    protected User(Parcel in) {
        uId = in.readString();
        username = in.readString();
        email = in.readString();
        phone = in.readString();
        role = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

package com.example.aoge.skillexchange;

import java.io.Serializable;

/**
 * Created by dell on 2019/3/21.
 */

public class UserInformation implements Serializable {
    private String userName;

    private String email;

    private String password;

    private String gender = "male";

    private String location ="null";

    private String can="null";

    private String want="null";

    private String headPicture;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
    }

    public String getheadPicture() {
        return headPicture;
    }

    public void setheadPicture(String headPicture) {
        this.headPicture = headPicture;
    }
}

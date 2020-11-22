package com.example.azorafinaltest.ui.Model;

public class UserItems {
    int UserId;
    int IMageID;
    public UserItems() {
    }

    public UserItems(int userId, int ImageID, String user, String image) {
        UserId = userId;
        IMageID = ImageID;
        User = user;
        Image = image;
    }
    public UserItems(int userId, int ImageID, String image) {
        UserId = userId;
        IMageID = ImageID;
        Image = image;
    }
    String User;
    String Image;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        IMageID = userId;
    }
    public int getIMageID() {
        return UserId;
    }

    public void setIMageID(int newsId) {
        IMageID = newsId;
    }
    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

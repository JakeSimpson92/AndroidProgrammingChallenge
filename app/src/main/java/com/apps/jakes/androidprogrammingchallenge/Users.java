package com.apps.jakes.androidprogrammingchallenge;

/**
 * Created by Jakes on 19/02/2015.
 */
public class Users {


    private String ID;
    private String ImageID;
    private String Title;
    private String UserID;
    private String UserName;

    public Users() {

    }

    public Users(String ID, String ImageID, String Title, String UserID,
                  String UserName) {
        super();
        this.ID = ID;
        this.ImageID = ImageID;
        this.Title = Title;
        this.UserID = UserID;
        this.UserName = UserName;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}

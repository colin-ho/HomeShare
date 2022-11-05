package com.example.homeshare.Model;

public class User {

    private String fullName;
    private String contactNumber;
    private String about;
    private Number userId;
    private String profilePic;
    private String[] rejectedInvitations;

    public User(String fullName, String contactNumber, String about, Number userId, String profilePic, String[] rejectedInvitations) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.about = about;
        this.userId = userId;
        this.profilePic = profilePic;
        this.rejectedInvitations = rejectedInvitations;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Number getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String[] getRejectedInvitations() {
        return rejectedInvitations;
    }

    public void setRejectedInvitations(String[] rejectedInvitations) {
        this.rejectedInvitations = rejectedInvitations;
    }
}

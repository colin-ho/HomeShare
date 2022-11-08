package com.example.homeshare.Model;

import java.util.ArrayList;

public class User {

    private String fullName;
    private String contactNumber;
    private String about;
    private String profilePic;
    private ArrayList<String> rejectedInvitations;
    private ArrayList<String> acceptedInvitations;

    public User(String fullName, String contactNumber, String about, String profilePic, ArrayList<String> rejectedInvitations,ArrayList<String> acceptedInvitations) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.about = about;
        this.profilePic = profilePic;
        this.rejectedInvitations = rejectedInvitations;
        this.acceptedInvitations = acceptedInvitations;
    }

    public User(){

    }

    public ArrayList<String> getAcceptedInvitations() {
        return acceptedInvitations;
    }

    public void setAcceptedInvitations(ArrayList<String> acceptedInvitations) {
        this.acceptedInvitations = acceptedInvitations;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<String> getRejectedInvitations() {
        return rejectedInvitations;
    }

    public void setRejectedInvitations(ArrayList<String> rejectedInvitations) {
        this.rejectedInvitations = rejectedInvitations;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", about='" + about + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", rejectedInvitations=" + rejectedInvitations +
                ", acceptedInvitations=" + acceptedInvitations +
                '}';
    }
}

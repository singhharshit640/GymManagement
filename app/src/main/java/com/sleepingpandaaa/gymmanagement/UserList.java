package com.sleepingpandaaa.gymmanagement;

public class UserList {

    private String Uid;
    private String nameFirst;
    private String lastName;
    private String plan;

    public UserList(String uid, String nameFirst, String lastName, String plan) {
        Uid = uid;
        this.nameFirst = nameFirst;
        this.lastName = lastName;
        this.plan = plan;
    }

    public UserList() {
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
package com.sleepingpandaaa.gymmanagement;

public class User {

    public String bloodGrp, date, emailId,gender,lastName,nameFirst,plan,userAge,userHeight,userWeight;

    public User(){

    }

    public User(String bloodGrp, String date, String emailId, String gender, String lastName, String nameFirst, String plan, String userAge, String userHeight, String userWeight) {
        this.bloodGrp = bloodGrp;
        this.date = date;
        this.emailId = emailId;
        this.gender = gender;
        this.lastName = lastName;
        this.nameFirst = nameFirst;
        this.plan = plan;
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }
}

package com.example.sravyadara.smarthomeproject;

/**
 * Created by sravyadara on 4/20/15.
 */
public class userDAO {

    static String firstName;
    static String lastName;
    static String emailId;
    static String userName;
    static String password;
    static String address;
    static String phoneNum;


    public userDAO(){

    }


    public userDAO(String firstName,String lastName,String emailId,String userName,String password,String address,String phoneNum)
    {

        this.firstName=firstName;
        this.lastName=lastName;
        this.emailId=emailId;
        this.userName=userName;
        this.password=password;
        this.address=address;
        this.phoneNum=phoneNum;

    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        userDAO.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        userDAO.lastName = lastName;
    }

    public static String getEmailId() {
        return emailId;
    }

    public static void setEmailId(String emailId) {
        userDAO.emailId = emailId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        userDAO.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        userDAO.password = password;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        userDAO.address = address;
    }

    public static String getPhoneNum() {
        return phoneNum;
    }

    public static void setPhoneNum(String phoneNum) {
        userDAO.phoneNum = phoneNum;
    }
}

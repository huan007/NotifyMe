package com.huannguyen.notifyme;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Huan Nguyen on 8/6/2017.
 * Represent a single user in the app. Contains user's information
 */

class User {
    String userName;
    String email;
    UserType userType;

    public User()
    {
        userName = null;
        email = null;
        userType = UserType.GUEST;
    }

    public User(String userName, String email, UserType userType)
    {
        this.userName = userName;
        this.email = email;
        this.userType = userType;
    }

    /* Setters and Getters (Generated) */
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    //Implemented using template provided by Firebase Official Tutorial
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", userName);
        result.put("email", email);
        result.put("usertype", userType);

        return result;
    }
}

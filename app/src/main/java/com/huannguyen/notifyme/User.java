package com.huannguyen.notifyme;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 *
 * Represent a single user in the app. Contains user's information
 */

class User{
    private String userName;
    private String email;
    private String UID;
    private UserType userType;

    public User()
    {
        userName = null;
        email = null;
        userType = UserType.GUEST;
    }

    public User(String userName, String email, String UID, UserType userType)
    {
        this.userName = userName;
        this.email = email;
        this.UID = UID;
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

    public String getUID() { return UID; }

    public void setUID(String UID) { this.UID = UID; }

    //Implemented using template provided by Firebase Official Tutorial
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", userName);
        result.put("email", email);
        result.put("UID", UID);
        result.put("usertype", userType);

        return result;
    }

    @Override
    public boolean equals(Object object)
    {
        //Check whether the object is a User object
        User userObject;
        if (object instanceof User)
            //casting object into User object
            userObject = (User) object;
        else
            return false;

        //Compare users' info
        if (this.userName != userObject.userName)
            return false;
        else if (this.email != userObject.email)
            return false;
        else if (this.UID != userObject.UID)
            return false;
        else if (this.userType != userObject.userType)
            return false;
        else
            return true;
    }
}

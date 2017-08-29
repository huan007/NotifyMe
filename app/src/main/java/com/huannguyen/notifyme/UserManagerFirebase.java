package com.huannguyen.notifyme;

/**
 * Created by Huan Nguyen on 8/28/2017.
 */

public class UserManagerFirebase implements UserManagerInterface {
    @Override
    public void registerUser(User newUser) {

    }

    @Override
    public User retrieveUser(String userID) {
        return null;
    }

    @Override
    public boolean checkUserExist(String userID) {
        return false;
    }
}

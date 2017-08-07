package com.huannguyen.notifyme;

/**
 * Created by Huan Nguyen on 8/6/2017.
 * This Interface is meant to be a guideline for further developments. I am using the Strategy pattern.
 */

public interface UserManagerInterface {

    /**
     * Register new User in the database
     * @param newUser User object containing new User's information
     */
    public void registerUser(User newUser);

    /**
     * Retreiving information about the user using their userID (From Google Sign In)
     * @param userID user's ID from Google Sign In
     * @return User object that contains the users's information
     */
    public User retrieveUser(String userID);

    /**
     * Check whether the user already exist in the database.
     * @param userID user's ID from Google Sign In
     * @return TRUE if the user is already exist
     */
    public boolean checkUserExist(String userID);
}

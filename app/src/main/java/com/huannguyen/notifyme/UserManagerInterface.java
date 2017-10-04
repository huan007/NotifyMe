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

    /**
     * Create a user space based on whether the user is a HOST or GUEST
     * @param userType  HOST or GUEST
     * @param userName name of the user
     * @param userEmail email address of the user
     * @param userID user ID
     * @param hostAddress (Only required for Host) address of host
     */
    public void createUserSpace(UserType userType, String userName, String userEmail, String userID,
                                String hostAddress);

    /**
     * Retrieve the user space based on user's ID
     * @param userID user ID
     * @return UserSpace object, can either be Host or Guest Object which extends UserSpace
     */
    public UserSpace retrieveUserSpace(String userID);
}

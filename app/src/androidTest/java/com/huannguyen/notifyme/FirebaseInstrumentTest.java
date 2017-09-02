package com.huannguyen.notifyme;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Huan Nguyen on 9/1/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FirebaseInstrumentTest {

    @Rule
    public ActivityTestRule signInActivityRule = new ActivityTestRule<>(
            SignInActivity.class);

    @Test
    public void testRegisterUser()
    {
        //Try to sign in
        onView(withId(R.id.signInButton)).perform(click());

        //Get infos about user
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        String userName = currUser.getDisplayName();
        String email = currUser.getEmail();
        String userID = currUser.getUid();

        //Create a new User object
        User newUser = new User(userName, email, userID, UserType.HOST);
        //Get Manager
        UserManagerInterface userManager = new UserManagerFirebase();

        //Register new User
        userManager.registerUser(newUser);

        //Check whether new user exist
        assertEquals(true, userManager.checkUserExist(userID));

        //Retrieve user and compare it with original user
        User retrievedUser = userManager.retrieveUser(userID);
        assertEquals(newUser, retrievedUser);
    }
}

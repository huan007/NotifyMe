package com.huannguyen.notifyme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRegistrationActivity extends AppCompatActivity {
    private static final String TAG = "UserRegistrationActivity";
    private static FirebaseUser currUser;
    private static UserManagerInterface userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get currUser and UserManager (Using Firebase for now)
        currUser = FirebaseAuth.getInstance().getCurrentUser();
        userManager = new UserManagerFirebase();

        //OnCreate, check whether the user is already exist
        //FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        //Log.d(TAG, "UID: " + currUser.getUid());

        //Skip Activity when the user is already exist
        startNextActivity();
    }

    public void onClickHost(View view)
    {
        User newUser = new User(currUser.getDisplayName(), currUser.getEmail(), currUser.getUid(),
                UserType.HOST);
        userManager.registerUser(newUser);
        startNextActivity();
    }

    public void onClickGuest(View view)
    {
        User newUser = new User(currUser.getDisplayName(), currUser.getEmail(), currUser.getUid(),
                UserType.GUEST);
        userManager.registerUser(newUser);
        startNextActivity();
    }

    public void startNextActivity()
    {

    }
}

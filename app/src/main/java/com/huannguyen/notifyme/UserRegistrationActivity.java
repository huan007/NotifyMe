package com.huannguyen.notifyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private static FirebaseUser currUser;
    private static UserManagerInterface userManager;
    ProgressBar loading;
    LinearLayout topLayout;
    LinearLayout bottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        //Get currUser and UserManager (Using Firebase for now)
        currUser = FirebaseAuth.getInstance().getCurrentUser();
        userManager = new UserManagerFirebase();
        loading = (ProgressBar) findViewById(R.id.loadingBar);
        topLayout  = (LinearLayout) findViewById(R.id.topLayout);
        bottomLayout = (LinearLayout) findViewById(R.id.bottomLayout);

        //OnCreate, check whether the user is already exist
        //FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        //Log.d(TAG, "UID: " + currUser.getUid());

        //Skip Activity when the user is already exist
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (userManager.checkUserExist(currUser.getUid()) == true)
                {
                    Log.d(TAG, "User is Already Registered!");
                    User userProfile = userManager.retrieveUser(currUser.getUid());
                    if (userProfile.getUserType() == UserType.HOST)
                        startHostActivity();
                    else if (userProfile.getUserType() == UserType.GUEST)
                        startGuestActivity();
                } else {
                    Log.d(TAG, "User is Not Registered");
                    loading.setVisibility(View.INVISIBLE);
                    topLayout.setVisibility(View.VISIBLE);
                    bottomLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        t.start();
    }

    public void onClickHost(View view)
    {
        User newUser = new User(currUser.getDisplayName(), currUser.getEmail(), currUser.getUid(),
                UserType.HOST);
        userManager.registerUser(newUser);
        startHostActivity();
    }

    public void onClickGuest(View view)
    {
        User newUser = new User(currUser.getDisplayName(), currUser.getEmail(), currUser.getUid(),
                UserType.GUEST);
        userManager.registerUser(newUser);
        startGuestActivity();
    }

    public void startHostActivity()
    {
        Intent hostIntent = new Intent(this, HostMainActivity.class);
        hostIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(hostIntent);

        finish();
    }

    public void startGuestActivity() {
        Intent guestIntent = new Intent(this, GuestMainActivity.class);
        guestIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(guestIntent);

        finish();
    }
}

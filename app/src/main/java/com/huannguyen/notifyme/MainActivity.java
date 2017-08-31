package com.huannguyen.notifyme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OnCreate, check whether the user is already exist
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "UID: " + currUser.getUid());

    }

    public void onClickHost(View view)
    {

    }

    public void onClickGuest(View view)
    {

    }
}

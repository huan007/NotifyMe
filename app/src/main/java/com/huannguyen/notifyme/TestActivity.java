package com.huannguyen.notifyme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TestActivity extends AppCompatActivity {
    HostReservationInterface hostReserve;
    UserManagerInterface userManager;
    UserSpace created;
    UserSpace retrieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        hostReserve = new HostReservationFirebase();
        userManager = new UserManagerFirebase();
    }

    public void onClickCreate(View view)
    {
        //Get infos about user
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        String userName = currUser.getDisplayName();
        String email = currUser.getEmail();
        String userID = currUser.getUid();

        created = userManager.createUserSpace(UserType.HOST, userName, email, "321 Main");
    }

    public void onClickRetrieve(View view)
    {
        //Get infos about user
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        String userName = currUser.getDisplayName();
        String email = currUser.getEmail();
        String userID = currUser.getUid();

        retrieved = userManager.retrieveUserSpace(UserType.HOST, userID, new FirebaseRetrievalInterface() {
            @Override
            public void onRetrieval(Object result) {
                handleRetrieve(result);
            }
        });

    }

    public void handleRetrieve(Object result){
        if (result instanceof Host)
        {
            //Parse the result
            retrieved = (Host) result;

        }
    }
}

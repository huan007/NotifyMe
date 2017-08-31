package com.huannguyen.notifyme;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Huan Nguyen on 8/28/2017.
 */

public class UserManagerFirebase implements UserManagerInterface {
    private final static String TAG = "UserManagerFirebase";
    @Override
    public void registerUser(User newUser) {
        //Get root reference
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        //Get UID
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //(FUTURE) Maybe add Security Measure?

        //Write new user into database
        rootRef.child("User/" + userID).setValue(newUser);
    }

    @Override
    public User retrieveUser(String userID) {
        //Get root reference
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("User/" + userID);

        final User[] user = new User[1];
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user[0] = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        };

        userRef.addListenerForSingleValueEvent(userListener);
        return user[0];
    }

    @Override
    public boolean checkUserExist(String userID) {
        //Delegate responsibility to retreiveUser. If null then user doesn't exist.
        if (retrieveUser(userID) != null)
            return true;
        else
            return false;
    }
}

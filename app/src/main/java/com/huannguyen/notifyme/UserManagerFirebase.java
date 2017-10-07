package com.huannguyen.notifyme;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Thread.sleep;

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
        Log.d(TAG, "UID: "  + userID);

        //Write new user into database
        rootRef.child("Users/" + userID).setValue(newUser);
    }

    @Override
    public User retrieveUser(String userID) {
        //Get root reference
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("Users/" + userID);

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

        /*Here I used while loop to wait until value is retrieved. Possibly infinite loop
         *if there are no internet connection*/
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user[0];
    }

    @Override
    public boolean checkUserExist(String userID) {
        //Delegate responsibility to retreiveUser. If null then user doesn't exist.
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference userRef = rootRef.child("Users/" + userID);

        final boolean[] result = new boolean[1];
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    result[0] = true;
                } else
                    result[0] = false;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });

        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    @Override
    public UserSpace createUserSpace(UserType userType, String userName, String userEmail, String hostAddress) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        UserSpace newUserSpace = null;
        if (userType == UserType.HOST)
        {//Creating user space for Host Type
            newUserSpace = new Host(userName, hostAddress, userEmail, userID);
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

            //Write new user space
            rootRef.child("Host/" + userID).setValue(newUserSpace);
        }

        else
        {//Creating user space for Guest Type

        }
        return newUserSpace;
    }

    @Override
    public UserSpace retrieveUserSpace(final UserType userType, String userID,
                                       final FirebaseRetrievalInterface retrievalInterface) {
        String dir;
        if (userType == UserType.HOST)
            dir = "Host/";
        else
            dir = "Guest/";
        //Get root reference
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userSpaceRef = rootRef.child(dir + userID);

        final UserSpace[] userSpaces = new UserSpace[1];
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (userType == UserType.HOST)
                    retrievalInterface.onRetrieval(dataSnapshot.getValue(Host.class));
                else
                    retrievalInterface.onRetrieval(dataSnapshot.getValue(Guest.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        };


        userSpaceRef.addListenerForSingleValueEvent(userListener);

        return userSpaces[0];
    }
}

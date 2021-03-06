package com.huannguyen.notifyme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 */

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    SignInButton signInButton;
    GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    FirebaseUser currUser = null;


    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        //Set up for Google Sign-Ins
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        //Get handle to signInButton
        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly. We can try and retrieve an
            // authentication code.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);

            Toast.makeText(SignInActivity.this.getBaseContext(),"Signed in with " + result.getSignInAccount().getEmail(),
                    Toast.LENGTH_LONG).show();
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statusCode = result.getStatus().getStatusCode();
            Log.d(TAG, "StatusCode: " + statusCode);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "SignIn Result: " + result.isSuccess());
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);

            //Wait until we get an actual user from Google Authentication
            while (FirebaseAuth.getInstance().getCurrentUser() == null)
            {
                //Empty block to wait
            }

            //Exit SignIn Activity, proceed to Main Activity
            Intent activityIntent = new Intent(this, UserRegistrationActivity.class);
            activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(activityIntent);
        } else {
            // Google Sign In failed, update UI appropriately
            // ...
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "ID:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {//Successfully authenticated with Google Sign-In
                            Log.d(TAG, "Authenticated with Google Token");
                            currUser = mAuth.getCurrentUser();
                        }

                        else
                        {//Failed
                            Log.d(TAG, "Failed to authenticated with Google Token");
                            Toast.makeText(SignInActivity.this, "Failed to Authenticate", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection Failed");
    }

    @Override
    public void onClick(View v) {
        signIn();
    }
}

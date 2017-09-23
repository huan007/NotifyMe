package com.huannguyen.notifyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_main);
    }

    public void onClickTicket(View view) {
        startTicketActivity();
    }

    public void startTicketActivity() {
        Intent ticketIntent = new Intent(this, GetTicketActivity.class);
        ticketIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ticketIntent);
    }
}

package com.huannguyen.notifyme;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 */

public class GuestMainActivity extends AppCompatActivity {
    public static Ticket ticket = new Ticket();
    private TextView waitNumber;
    private TextView waitID;
    private TextView storeName;
    private TextView storeAddr;

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

    @Override
    public void onResume(){
        super.onResume();

        storeName = (TextView) findViewById(R.id.storeName);
        storeAddr = (TextView) findViewById(R.id.storeAddr);
        waitNumber = (TextView) findViewById(R.id.WaitNumber);
        waitID = (TextView) findViewById(R.id.WaitID);

        if (ticket.getStoreName() == null){
            ticket.setStoreName("N/A");
            ticket.setStoreAddress("N/A");
            ticket.setTicketNumber(0);
            ticket.setTicketID("N/A");
        }

        storeName.setText("Store name: " + ticket.getStoreName());
        storeAddr.setText("Store address: " + ticket.getStoreAddress());
        waitNumber.setText(String.valueOf(ticket.getTicketNumber()));
        waitID.setText(ticket.getTicketID());

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

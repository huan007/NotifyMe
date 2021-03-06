package com.huannguyen.notifyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import me.sudar.zxingorient.Barcode;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

import static android.Manifest.permission.CAMERA;
import static com.huannguyen.notifyme.GuestMainActivity.ticket;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 */

public class GetTicketActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final int REQUEST_CAMERA = 1;
    private String qrlink = null;
    private boolean isPlaceHaveService;
    public static UserManagerFirebase userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ticket);

        userManager = new UserManagerFirebase();

        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.search);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                isPlaceHaveService = userManager.checkHostExistGMap(place.getAddress().toString().replaceAll(",", ""), new FirebaseRetrievalInterface() {
                    @Override
                    public void onRetrieval(Object result) {
                        handleRetrieve(place, result);
                    }
                });

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void onClickFinish(View v){
        finish();
    }

    public void onClickQR(View v){
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                showScanner();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        ZxingOrientResult scanResult =
                ZxingOrient.parseActivityResult(requestCode, resultCode, intent);

        if (scanResult != null) {
            Log.d("TEST", "" + scanResult.getContents());

            String[] result = scanResult.getContents().split("/");

            for (int i = 0; i < result.length; i++){
                Log.d("TEST3", "" + result[i]);
            }
        }
    }

    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        showScanner();
                    }else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(GetTicketActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void showScanner(){
        ZxingOrient integrator = new ZxingOrient(GetTicketActivity.this);
        integrator.setIcon(R.mipmap.ic_launcher_round)
                .setToolbarColor("#0277BD")       // Sets Tool bar Color
                .setInfoBoxColor("#01579B")       // Sets Info box color
                .setInfo("Scan a QR code Image")   // Sets info message in the info box
                .showInfoBox(false)
                .setVibration(false)
                .setBeep(false)
                .initiateScan(Barcode.QR_CODE);
    }

    public void handleRetrieve(Place place, Object result){
        isPlaceHaveService = (Boolean) result;

        if (isPlaceHaveService == true){
            ticket.setStoreName(place.getName().toString());
            ticket.setStoreAddress(place.getAddress().toString());
            ticket.setTicketNumber(5);
            ticket.setTicketID("JAPOSEA0005");

            mMap.addMarker(new MarkerOptions().position(place.getLatLng())
                    .title(place.getName().toString()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),15));
        } else {
            Toast.makeText(GetTicketActivity.this.getBaseContext(),"Sorry but the service is not available at this store",
                    Toast.LENGTH_LONG).show();
        }
    }
}

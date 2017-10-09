package com.huannguyen.notifyme;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 */

class Host extends UserSpace implements HostReservationInterface{
    //Basic Info
    private String hostName;
    private String hostAddress;
    private String hostEmail;
    private String UID;
    private int currentNumber;
    private int nextNumber;
    final private int MAX_NUMBER = 1000;
    //List of reservation
    private List<Reservation> reservationList;

    public Host()
    {
        reservationList = new LinkedList<Reservation>();
        currentNumber = 0;
        nextNumber = 1;
    }

    public Host(String hostName, String hostAddress, String hostEmail, String UID){
        this();
        this.hostName = hostName;
        this.hostAddress = hostAddress;
        this.hostEmail = hostEmail;
        this.UID = UID;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getNextNumber() { return nextNumber; }

    public int generateNextNumber(){
        int returnNumber = nextNumber;
        nextNumber++;
        if (nextNumber > MAX_NUMBER)
            nextNumber = 1;
        return returnNumber;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public Reservation createReservation(String reservationID, Reservation newReservation) {
        //Creating new blank reservation
        Reservation reservation = new Reservation(getHostName(), getHostAddress(), getUID(), generateNextNumber());

        //Plan: Add new reservation into the list, then update it on firebase
        reservationList.add(reservation);

        //Get reference to reservation
        DatabaseReference userSpaceRef = FirebaseDatabase.getInstance().getReference().child("Host/" + UID);
        return null;
    }

    @Override
    public boolean deleteReservation(String reservationID) {
        return false;
    }

    @Override
    public boolean setReservationReady(String reservationID) {
        return false;
    }
}

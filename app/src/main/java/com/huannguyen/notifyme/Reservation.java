package com.huannguyen.notifyme;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 */

class Reservation {
    private String hostName;
    private String hostAddress;
    private String hostID;
    private int reservationNumber;
    private String customerName;
    private String customerID;
    private int numberOfGuest;
    private boolean ready;

    public Reservation()
    {
        ready = false;
    }

    //This constructor is used by the Host to create a blank reservation. This reservation should be
    public Reservation(String hostName, String hostAddress, String hostID, int reservationNumber) {
        this();
        this.hostName = hostName;
        this.hostAddress = hostAddress;
        this.hostID = hostID;
        this.reservationNumber = reservationNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("hostName", hostName);
        result.put("hostAddress", hostAddress);
        result.put("hostID", hostID);
        result.put("reservationNumber", reservationNumber);
        result.put("customerName", customerName);
        result.put("customerID", customerID);
        result.put("numberOfGuest", numberOfGuest);
        result.put("ready", ready);

        return result;
    }
}

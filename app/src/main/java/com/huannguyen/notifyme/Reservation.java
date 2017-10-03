package com.huannguyen.notifyme;

/**
 * Created by Huan Nguyen on 10/2/2017.
 */

class Reservation {
    private String hostName;
    private String hostAddress;
    private String hostID;
    private int reservationNumber;
    private String customerName;
    private String customerID;
    private boolean ready;

    public Reservation()
    {
        ready = false;
    }

    //This constructor is used by the Host to create a blank reservation. This reservation should be
    public Reservation(String hostName, String hostAddress, String hostID, int reservationNumber) {
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
}

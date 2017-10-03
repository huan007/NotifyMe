package com.huannguyen.notifyme;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Huan Nguyen on 10/2/2017.
 */

class Host {
    //Basic Info
    private String hostName;
    private String hostAddress;
    private String UID;
    private int currentNumber;
    //List of reservation
    private List<Reservation> reservationList;

    public Host()
    {
        reservationList = new LinkedList<Reservation>();
        currentNumber = 0;
    }

    public Host(String hostName, String hostAddress, String UID){
        this.hostName = hostName;
        this.hostAddress = hostAddress;
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}

package com.huannguyen.notifyme;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 *
 * Represent a single ticket in the app. Contains ticket's information
 */

class Ticket {
    private String storeName;
    private String storeAddress;
    private int ticketNumber;
    private String ticketID;

    public Ticket(){
        storeName = null;
        storeAddress = null;
        ticketNumber = 0;
        ticketID = null;
    }

    public Ticket(String storeName, String storeAddress, int ticketNumber, String ticketID){
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.ticketNumber = ticketNumber;
        this.ticketID = ticketID;
    }

    public String getStoreName(){
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getTicketID() {return ticketID;}

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }

    public void setStoreAddress(String storeAddress){
        this.storeAddress = storeAddress;
    }

    public void setTicketNumber(int ticketNumber){
        this.ticketNumber = ticketNumber;
    }

    public void setTicketID(String ticketID){this.ticketID = ticketID;}
}

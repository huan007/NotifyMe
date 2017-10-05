package com.huannguyen.notifyme;

/**
 * Created by BaSiDin on 9/22/2017.
 * Represent a single ticket in the app. Contains ticket's information
 */

class Ticket {
    private String storeName;
    private String storeAddress;
    private int storeNumber;

    public Ticket(){
        storeName = null;
        storeAddress = null;
        storeNumber = 0;
    }

    public Ticket(String storeName, String storeAddress, int storeNumber){
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeNumber = storeNumber;
    }

    public String getStoreName(){
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }

    public void setStoreAddress(String storeAddress){
        this.storeAddress = storeAddress;
    }

    public void setStoreNumber(int storeNumber){
        this.storeNumber = storeNumber;
    }
}

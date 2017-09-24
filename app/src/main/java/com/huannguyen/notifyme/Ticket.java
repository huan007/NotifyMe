package com.huannguyen.notifyme;

import java.io.Serializable;

/**
 * Created by BaSiDin on 9/22/2017.
 * Represent a single ticket in the app. Contains ticket's information
 */

class Ticket {
    private String storeName;
    private String storeAddress;
    private int currentNumber;

    public Ticket(){
        storeName = null;
        storeAddress = null;
        currentNumber = 0;
    }

    public Ticket(String storeName, String storeAddress, int currentNumber){
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.currentNumber = currentNumber;
    }

    public String getStoreName(){
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }

    public void setStoreAddress(String storeAddress){
        this.storeAddress = storeAddress;
    }

    public void setCurrentNumber(int currentNumber){
        this.currentNumber = currentNumber;
    }
}

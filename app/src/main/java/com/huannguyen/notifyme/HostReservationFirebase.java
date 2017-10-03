package com.huannguyen.notifyme;

/**
 * Created by Huan Nguyen on 10/2/2017.
 */

class HostReservationFirebase implements HostReservationInterface {

    //For now, reservationID will be the reservation number given by the HOST
    @Override
    public Reservation createReservation(String reservationID, Reservation newReservation) {
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

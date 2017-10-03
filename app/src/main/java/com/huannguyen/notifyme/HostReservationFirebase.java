package com.huannguyen.notifyme;

/**
 * Created by Huan Nguyen on 10/2/2017.
 */

class HostReservationFirebase implements HostReservationInterface {
    @Override
    public Reservation createReservation(String reservationID) {
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

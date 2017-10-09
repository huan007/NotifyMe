package com.huannguyen.notifyme;

/**
 * Author:  Huan Nguyen (cyraxnguyen & huan007)
 *          Anh Khoa Nguyen Vu (leScepter & akhoa.nv)
 */

public interface HostReservationInterface {

    /**
     * Create a blank reservation in the Database
     * @param reservationID unique ID for the reservation
     * @return created reservation. Return NULL if failed
     */
    public Reservation createReservation(String reservationID, Reservation newReservation);

    /**
     * Delete an existing reservation
     * @param reservationID unique ID for the reservation
     * @return return TRUE if successfully deleted the reservation
     */
    public boolean deleteReservation(String reservationID);

    /**
     * Mark the reservation as Ready so customer will be notified
     * @param reservationID unique ID for the reservation
     * @return return TRUE if successfully updated the reservation
     */
    public boolean setReservationReady(String reservationID);
}

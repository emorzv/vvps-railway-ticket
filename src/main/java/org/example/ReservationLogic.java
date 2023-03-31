package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationLogic {
    private Database database;
    // private List<Reservation> reservations;

    public ReservationLogic(Database database) {
        this.database = database;
        // this.reservations = database.getReservations();
    }

    public void createReservation(Reservation reservation) {
        List<Reservation> reservations = database.getReservations();
        reservation.setReservationID(database.getLastReservationID() + 1);
        reservation.setCreatedOn(LocalDate.now().toEpochDay());
        reservations.add(reservation);
    }

    public void viewPreviousReservations(String EGN) {
        List<Reservation> reservations = database.getReservations();
        List<Reservation> reservationsToBeDeleted = new ArrayList<>();
        boolean isEmpty = true;

        for (Reservation res :
                reservations) {
            if (LocalDate.now().toEpochDay() > (res.getCreatedOn() + 7)) {
                reservationsToBeDeleted.add(res);
            }
        }
        reservations.removeAll(reservationsToBeDeleted);

        for (Reservation res :
                reservations) {
            if (res.getPersonEGN().equals(EGN)) {
                res.customToString();
                isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println("No reservations on this EGN!");
        }
    }

    public void deleteReservationByID(String EGN, int reservationID) {
        List<Reservation> reservations = database.getReservations();
        List<Reservation> reservationsToBeDeleted = new ArrayList<>();
        boolean isEmpty = true;

        for (Reservation res :
                reservations) {
            if (res.getPersonEGN().equals(EGN) && res.getReservationID() == reservationID) {
                reservationsToBeDeleted.add(res);
                isEmpty = false;
            }
        }
        reservations.removeAll(reservationsToBeDeleted);

        if (isEmpty) {
            System.out.println("No reservations on this EGN!");
        }
    }

    public void updateReservationByID(String EGN, int reservationID, CardType newCardType) {
        List<Reservation> reservations = database.getReservations();
        boolean isEmpty = true;

        for (Reservation res :
                reservations) {
            if (res.getPersonEGN().equals(EGN) && res.getReservationID() == reservationID) {
                res.setDiscountType(newCardType);
                isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println("No reservations on this EGN!");
        }
    }

}

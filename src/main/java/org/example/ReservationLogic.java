package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationLogic {
    private Database database;
    private static final String ERROR_MESSAGE = "No reservations on this egn!";

    public ReservationLogic(Database database) {
        this.database = database;
    }

    public void createReservation(Reservation reservation) {
        List<Reservation> reservations = database.getReservations();
        reservation.setReservationID(database.getLastReservationID() + 1);
        reservation.setCreatedOn(LocalDate.now().toEpochDay());
        reservations.add(reservation);
    }

    public void viewPreviousReservations(String egn) {
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
            if (res.getPersonEGN().equals(egn)) {
                res.customToString();
                isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public void deleteReservationByID(String egn, int reservationID) {
        List<Reservation> reservations = database.getReservations();
        List<Reservation> reservationsToBeDeleted = new ArrayList<>();
        boolean isEmpty = true;

        for (Reservation res :
                reservations) {
            if (res.getPersonEGN().equals(egn) && res.getReservationID() == reservationID) {
                reservationsToBeDeleted.add(res);
                isEmpty = false;
            }
        }
        reservations.removeAll(reservationsToBeDeleted);

        if (isEmpty) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public void updateReservationByID(String egn, int reservationID, CardType newCardType) {
        List<Reservation> reservations = database.getReservations();
        boolean isEmpty = true;

        for (Reservation res :
                reservations) {
            if (res.getPersonEGN().equals(egn) && res.getReservationID() == reservationID) {
                res.setDiscountType(newCardType);
                isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println(ERROR_MESSAGE);
        }
    }

}

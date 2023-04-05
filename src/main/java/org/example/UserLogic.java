package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserLogic {
    private Database database;
    private static final String ERROR_MESSAGE = "No users with this ID!";

    public UserLogic(Database database) {
        this.database = database;
    }

    public void createUser(User user) {
        List<User> users = database.getUsers();
        user.setUserId(database.getLastUserId() + 1);
        users.add(user);
    }

    public void viewAllUsers() {
        List<User> users = database.getUsers();
        int counter = 1;
        boolean isEmpty = true;

        for (User user : users) {
            System.out.println();
            System.out.println("User [" + counter++ + "]");
            System.out.println("-------------------");
            user.customToString();
            isEmpty = false;
        }

        if (isEmpty) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public void updateUserCardType(int userId, CardType newCardType) {
        List<User> users = database.getUsers();

        for (User user: users) {
            if (user.getUserId() == userId) {
                user.setCard(newCardType);

                for (Reservation res : user.getReservations()) {
                    res.setDiscountType(newCardType);
                }
                break;
            }
        }
    }

    public User getSingleUser(String egn) {
        for (User user : database.getUsers()) {
            if (user.getEgn().equals(egn)) {
                return user;
            }
        }
        return null;
    }

    public void addReservationToUser(String currentUserEgn, Reservation reservation) {
        for (User user : database.getUsers()) {
            if (user.getEgn().equals(currentUserEgn)) {
                user.getReservations().add(reservation);
            }
        }
    }
}

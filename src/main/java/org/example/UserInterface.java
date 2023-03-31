package org.example;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        // SetUp
        Database database = new Database();
        ReservationLogic reservationLogic = new ReservationLogic(database);
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        database.readFromFileOnStartUp();

        Scanner scanner = new Scanner(System.in);

        RoleType role;

        // Prompt user or admin
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("--------------");
        System.out.print("input: ");
        role = RoleType.values()[Integer.parseInt(scanner.nextLine())];

        // Functionality depending on the role
        if (RoleType.USER == role) {
            System.out.println();
            System.out.println("Welcome User!");
            System.out.println("-------------");

            // Prompt EGN
            System.out.print("Enter your EGN: ");
            String EGN = scanner.nextLine();

            // Main lifecycle
            userOptions(EGN, reservationLogic, railwayTicketer, database);
        } else if (RoleType.ADMIN == role) {
            // TODO:
            adminOptions();
        }
    }

    // TODO:
    private static void adminOptions() {

    }

    private static void userOptions(
            String EGN,
            ReservationLogic reservationLogic,
            RailwayTicketer railwayTicketer,
            Database database) {
        boolean running = true;
        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            printMenu();
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    System.out.print("Person's name: ");
                    String name = scanner.nextLine();

                    System.out.println("Person's discount type: ");
                    System.out.println("0. No card");
                    System.out.println("1. Older than sixty card");
                    System.out.println("2. Family card");
                    CardType cardType = CardType.values()[Integer.parseInt(scanner.nextLine())];
                    railwayTicketer.setAvailableCard(cardType);

                    System.out.print("Traveling with a child (true/false): ");
                    boolean haveChild = Boolean.parseBoolean(scanner.nextLine());
                    railwayTicketer.setTravelingWithChild(haveChild);

                    System.out.print("One way ticket (true/false): ");
                    boolean oneWayTicket = Boolean.parseBoolean(scanner.nextLine());
                    railwayTicketer.setOneWayTicket(oneWayTicket);

                    System.out.print("Person's ticket count: ");
                    int ticketCount = Integer.parseInt(scanner.nextLine());
                    double[] ticketsPrices = new double[ticketCount];
                    for (int i = 0; i < ticketCount; i++) {
                        ticketsPrices[i] = railwayTicketer.calculateTwoWayTickets(
                                railwayTicketer.findAvailableTrainByPriceAndRoute());
                    }

                    Reservation newReservation = new Reservation(name, EGN, cardType, ticketCount);
                    newReservation.setTicketsPrices(ticketsPrices); // should not be here
                    reservationLogic.createReservation(newReservation);
                    break;
                case "2":
                    System.out.print("Input reservation's ID to be updated: ");
                    int reservationIDToBeUpdated = Integer.parseInt(scanner.nextLine());
                    System.out.println("Input new discount type: ");
                    System.out.println("0. No card");
                    System.out.println("1. Older than sixty card");
                    System.out.println("2. Family card");
                    CardType newCardType = CardType.values()[Integer.parseInt(scanner.nextLine())];
                    railwayTicketer.setAvailableCard(newCardType); // should not be here
                    reservationLogic.updateReservationByID(EGN, reservationIDToBeUpdated, newCardType);
                    break;
                case "3":
                    System.out.print("Input reservation's ID to be deleted: ");
                    int reservationIDToBeDeleted = Integer.parseInt(scanner.nextLine());
                    reservationLogic.deleteReservationByID(EGN, reservationIDToBeDeleted);
                    break;
                case "4":
                    reservationLogic.viewPreviousReservations(EGN);
                    break;
                case "5":
                    running = false;
                    // Write back to the db
                    database.writeToFile(database.getReservations());
                    break;
                default:
                    System.out.println("ERROR choosing an option");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Select an option");
        System.out.println("----------------");
        System.out.println("1. Create a reservation");
        System.out.println("2. Update a reservation");
        System.out.println("3. Delete a reservation");
        System.out.println("4. See previous reservations");
        System.out.println("5. Exit");
        System.out.println("-------------------");
        System.out.print("Input: ");
    }
}

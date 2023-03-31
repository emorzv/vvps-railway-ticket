package org.example;


public class Reservation {
    private int reservationID;
    private String personName;
    private String personEGN;
    private CardType discountType;
    private int ticketCount;
    private double[] ticketsPrices;
    private long createdOn;

    public Reservation() {
    }

    public Reservation(String personName, String personEGN, CardType discountType, int ticketCount) {
        this.personName = personName;
        this.personEGN = personEGN;
        this.discountType = discountType;
        this.ticketCount = ticketCount;
        this.ticketsPrices = new double[ticketCount];
    }

    public int getReservationID() {
        return this.reservationID;
    }

    public double[] getTicketsPrices() {
        return this.ticketsPrices;
    }

    public void setTicketsPrices(double[] ticketsPrices) {
        this.ticketsPrices = ticketsPrices;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEGN() {
        return personEGN;
    }

    public void setPersonEGN(String getPersonLastName) {
        this.personEGN = getPersonLastName;
    }

    public CardType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(CardType discountType) {
        this.discountType = discountType;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setCreatedOn(long localDate) {
        this.createdOn = localDate;
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void customToString() {
        System.out.println("Reservation ID: " + this.getReservationID());
        System.out.println("Person's EGN: " + this.getPersonEGN());
        System.out.println("Person's name: " + this.getPersonName());
        System.out.println("Discount type: " + this.getDiscountType());
        System.out.println("Number of reserved tickets: " + this.getTicketCount());
        System.out.println("------------------------------------------------------");
    }
}


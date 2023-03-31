package org.example;

import org.example.CardType;
import org.example.RailwayTicketer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RailwayTicketerTest {
    @Test
    @DisplayName(
            "The calculated discount price should equal to 50, " +
                    "with a family card and a child")
    void calculatedDiscountPriceShouldBe50() {
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        railwayTicketer.setAvailableCard(CardType.FAMILY_CARD);
        railwayTicketer.setOneWayTicket(true);
        railwayTicketer.setTravelingWithChild(true);
        railwayTicketer.setOneWayTicket(true);

        Assertions.assertEquals(50, railwayTicketer.calculateTwoWayTickets(100));
    }

    @Test
    @DisplayName(
            "The calculated discount price should equal to 66, " +
                    "with an older than sixty card")
    void calculatedDiscountPriceShouldBe66() {
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        railwayTicketer.setAvailableCard(CardType.OLDER_THAN_SIXTY_CARD);
        railwayTicketer.setOneWayTicket(true);
        railwayTicketer.setTravelingWithChild(true);
        railwayTicketer.setOneWayTicket(true);

        Assertions.assertEquals(66, railwayTicketer.calculateTwoWayTickets(100));
    }

    @Test
    @DisplayName(
            "The calculated discount price should equal to 90, " +
                    "with a family card without a child")
    void calculatedDiscountPriceShouldBe90() {
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        railwayTicketer.setAvailableCard(CardType.FAMILY_CARD);
        railwayTicketer.setOneWayTicket(true);
        railwayTicketer.setTravelingWithChild(false);
        railwayTicketer.setOneWayTicket(true);

        Assertions.assertEquals(90, railwayTicketer.calculateTwoWayTickets(100));
    }

    @Test
    @DisplayName(
            "The calculated discount price should equal to 95, " +
                    "without a card rush hour discount only")
    void calculatedDiscountPriceShouldBe95() {
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        railwayTicketer.setAvailableCard(CardType.NOT_SET);
        railwayTicketer.setOneWayTicket(true);
        railwayTicketer.setTravelingWithChild(false);
        railwayTicketer.setRushHour(false);
        railwayTicketer.setOneWayTicket(true);

        Assertions.assertEquals(95, railwayTicketer.calculateTwoWayTickets(100));
    }

    @Test
    @DisplayName(
            "The calculated discount price should equal to 100, " +
                    "without a card and no rush hour discount")
    void calculatedDiscountPriceShouldBe100() {
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        railwayTicketer.setAvailableCard(CardType.NOT_SET);
        railwayTicketer.setOneWayTicket(true);
        railwayTicketer.setTravelingWithChild(false);
        railwayTicketer.setRushHour(true);
        railwayTicketer.setOneWayTicket(true);

        Assertions.assertEquals(100, railwayTicketer.calculateTwoWayTickets(100));
    }

    @Test
    @DisplayName(
            "The calculated discount price should equal to 200, " +
                    "without a card and no rush hour discount, but two way ticket")
    void calculatedDiscountPriceShouldBe200() {
        RailwayTicketer railwayTicketer = new RailwayTicketer();
        railwayTicketer.setAvailableCard(CardType.NOT_SET);
        railwayTicketer.setOneWayTicket(true);
        railwayTicketer.setTravelingWithChild(false);
        railwayTicketer.setRushHour(true);
        railwayTicketer.setOneWayTicket(false);

        Assertions.assertEquals(200, railwayTicketer.calculateTwoWayTickets(100));
    }

}

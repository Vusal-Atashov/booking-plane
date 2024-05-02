package dao;

import java.util.Objects;

public class FlightsEntity {
    public static long MAX_ID = 0;
    private final long id;
    private String destination;
    private String date;
    private int numOfSeats;

    public FlightsEntity(String destination, String date, int numOfSeats) {
        this.id = ++MAX_ID;
        this.destination = destination;
        this.date = date;
        this.numOfSeats = numOfSeats;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightsEntity that = (FlightsEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("{id='%s', destination='%s', date='%s', numOfSeats=%d}", id, destination, date, numOfSeats);
    }
}
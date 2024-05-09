package dto;


import java.util.Objects;

public class FlightDTO {
    public static int MAX_ID = 0;
    private int id;
    private String destination;
    private String date;
    private int numOfSeats;

    public FlightDTO() {
        this.id = ++MAX_ID;
    }

    public FlightDTO(String destination, String date, int numOfSeats) {
        this.id = ++MAX_ID;
        this.destination = destination;
        this.date = date;
        this.numOfSeats = numOfSeats;
    }

    public FlightDTO(int id, String destination, String date, int numOfSeats) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.numOfSeats = numOfSeats;
    }

    public int getId() {
        return id;
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
        FlightDTO flightDTO = (FlightDTO) o;
        return id == flightDTO.id && numOfSeats == flightDTO.numOfSeats && Objects.equals(destination, flightDTO.destination) && Objects.equals(date, flightDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, date, numOfSeats);
    }

    @Override
    public String toString() {
        return String.format("{id=%d, destination='%s', date='%s', numOfSeats=%d}", id, destination, date, numOfSeats);
    }
}
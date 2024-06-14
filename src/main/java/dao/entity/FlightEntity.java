package dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static long MAX_ID = 0;
    private long id;
    private Cities origin;
    private Cities destination;
    private LocalDateTime departureTime;
    private int numOfSeats;

    public FlightEntity(Cities origin, Cities destination, LocalDateTime departureTime, int numOfSeats) {
        this.id = ++MAX_ID;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.numOfSeats = numOfSeats;
    }

    public FlightEntity(long l, int i) {
    }

    public FlightEntity(long id, Cities origin, Cities destination, LocalDateTime departureTime, int numOfSeats) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.numOfSeats = numOfSeats;
    }

    public long getId() {
        return id;
    }

    public Cities getDestination() {
        return destination;
    }

    public void setDestination(Cities destination) {
        this.destination = destination;
    }

    public Cities getOrigin() {
        return origin;
    }

    public void setOrigin(Cities origin) {
        this.origin = origin;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
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
        FlightEntity that = (FlightEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("\n" + "id='%s', destination='%s', date='%s', numOfSeats=%d}", id, destination, departureTime, numOfSeats);
    }
}

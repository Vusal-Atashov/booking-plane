package dto;


import dao.entity.Cities;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightDto {
    @Serial
    private static final long serialVersionUID =1L;
    public static long MAX_ID = 0;
    private long id;
    private Cities origin;
    private Cities destination;
    private LocalDateTime departureTime;
    private int numOfSeats;

    public FlightDto(Cities origin, Cities destination, LocalDateTime departureTime, int numOfSeats) {
        this.id = ++MAX_ID;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.numOfSeats = numOfSeats;
    }

    public FlightDto() {
    }

    public FlightDto(long id, Cities origin, Cities destination, LocalDateTime departureTime, int numOfSeats) {
        this.origin = origin;
        this.id = id;
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
        FlightDto flightDto = (FlightDto) o;
        return id == flightDto.id && numOfSeats == flightDto.numOfSeats && origin == flightDto.origin && destination == flightDto.destination && Objects.equals(departureTime, flightDto.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\n"+"{id=%d, origin=%s, destination=%s, departureTime=%s, numOfSeats=%d}".formatted(id, origin, destination, departureTime, numOfSeats);
    }
}
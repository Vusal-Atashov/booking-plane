package dto;

import java.util.List;
import java.util.Objects;

public class BookingDto {

    private static long MAX_ID = 0;
    private long id;
    private long flightId;
    private List<String> passengerNames;

    public BookingDto() {
    }

    public BookingDto(long id, long flightId, List<String> passengerNames) {
        this.id = id;
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public BookingDto(long flightId, List<String> passengerNames) {
        this.id = ++MAX_ID;
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public long getId() {
        return id;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public List<String> getPassengerNames() {
        return passengerNames;
    }

    public void setPassengerNames(List<String> passengerNames) {
        this.passengerNames = passengerNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return id == that.id && Objects.equals(flightId, that.flightId) && Objects.equals(passengerNames, that.passengerNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightId, passengerNames);
    }

    @Override
    public String toString() {
        return String.format("{id=%d, flightId='%s', passengerNames=%s}", id, flightId, passengerNames);
    }
}



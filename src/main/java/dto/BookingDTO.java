package dto;

import java.util.List;
import java.util.Objects;

public class BookingDTO {

    private static long MAX_ID = 0;
    private long id;
    private int flightId;
    private List<String> passengerNames;

    public BookingDTO() {
        this.id = ++MAX_ID;
    }

    public BookingDTO(long id, int flightId, List<String> passengerNames) {
        this.id = id;
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public BookingDTO(int flightId, List<String> passengerNames) {
        this.id = ++MAX_ID;
        this.flightId = flightId;
        this.passengerNames = passengerNames;
    }

    public long getId() {
        return id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFlightId(int flightId) {
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
        BookingDTO that = (BookingDTO) o;
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



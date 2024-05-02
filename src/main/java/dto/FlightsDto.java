package dto;

public class FlightsDto {
    private final String destination;
    private final String date;
    private final int numOfSeats;
    private String id;

    public FlightsDto(String destination, String date, int numOfSeats) {
        this.destination = destination;
        this.date = date;
        this.numOfSeats = numOfSeats;
    }

    public FlightsDto(String id, String destination, String date, int numOfSeats) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.numOfSeats = numOfSeats;
    }

    @Override
    public String toString() {
        return String.format("{id='%s', destination='%s', date='%s', numOfSeats=%d}", id, destination, date, numOfSeats);
    }
}
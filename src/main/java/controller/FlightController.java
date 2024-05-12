package controller;

import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightController {
    void saveAllToFile();

    void getAllFromFile();

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(long flightId);

    void saveFlight(FlightDto flightDto);

    List<FlightDto> getFlightsByCriteria(String destination, LocalDateTime dateTime, int seats);

    List<FlightDto> getNext24HoursFlights(String origin);
}

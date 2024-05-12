package controller;

import dto.CriteriaDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightController {
    void saveAllToFile();

    void getAllFromFile();

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(long flightId);

    void saveFlight(FlightDto flightDto);

    List<FlightDto> getFlightsByCriteria(CriteriaDto criteria);

    List<FlightDto> getNext24HoursFlights(String origin);
}

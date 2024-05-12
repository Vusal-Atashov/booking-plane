package services;

import dto.CriteriaDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    void saveAllToFile();

    void getAllFromFile();

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(long flightId);

    List<FlightDto> getFlightsByCriteria(CriteriaDto criteria);

    void saveFlight(FlightDto flightDto);

    List<FlightDto> getNext24HoursFlights(String origin);
}

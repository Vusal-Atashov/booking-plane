package services;

import dao.entity.FlightEntity;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface FlightService {
    void saveAllToFile();

    void getAllFromFile();

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(long flightId);

    List<FlightDto> getFlightsByCriteria(String destination, LocalDateTime dateTime, int seats);

    void saveFlight(FlightDto flightDto);
}

package controller;

import dao.entity.Cities;
import dto.CriteriaDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightController {
    List<FlightDto> findByOrigin(String origin);

    void cancelFlight(int flightId);

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(long flightId);

    void saveFlight(FlightDto flightDto);

    List<FlightDto> getFlightsByCriteria(CriteriaDto criteria);

    List<FlightDto> getNext24HoursFlights(Cities origin);
}

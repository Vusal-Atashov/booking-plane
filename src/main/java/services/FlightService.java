package services;

import dao.entity.Cities;
import dto.CriteriaDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<FlightDto> findByOrigin(String origin);

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(long flightId);

    void cancelFlight(int flightId);
    List<FlightDto> getFlightsByCriteria(CriteriaDto criteria);

    void saveFlight(FlightDto flightDto);

    List<FlightDto> getNext24HoursFlights(Cities origin);
}

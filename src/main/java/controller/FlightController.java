package controller;

import dto.FlightDTO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface FlightController {
    void saveFlights(Collection<FlightDTO> flights);

    Collection<FlightDTO> getAllFlights();

    FlightDTO getFlightById(int flightId);

    List<FlightDTO> getAllFlightsByDestination(String destination);

    List<FlightDTO> getAllFlightsByDate(LocalDateTime date);

    void cancelFlight(int flightId);
}

package controller;

import dto.FlightDTO;

import java.util.Collection;
import java.util.List;

public interface FlightController {
    void saveFlights(Collection<FlightDTO> flights);

    Collection<FlightDTO> getAllFlights();

    FlightDTO getFlightById(int flightId);

    List<FlightDTO> getAllFlightsByDestination(String destination);

    List<FlightDTO> getAllFlightsByDate(String date);

    void cancelFlight(int flightId);
}

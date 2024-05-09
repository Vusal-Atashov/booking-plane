package controller;

import dto.FlightDTO;
import services.FlightService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class FlightControllerIMPL implements FlightController {
    private final FlightService flightService;

    public FlightControllerIMPL(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public void saveFlights(Collection<FlightDTO> flights) {
        flightService.saveFlights(flights);
    }

    @Override
    public Collection<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @Override
    public FlightDTO getFlightById(int flightId) {
        return flightService.getOneFlightBy(flights -> flights.getId()==flightId);
    }

    @Override
    public List<FlightDTO> getAllFlightsByDestination(String destination) {
        return flightService.getAll(flight -> flight.getDestination().equals(destination));
    }

    @Override
    public List<FlightDTO> getAllFlightsByDate(LocalDateTime date) {
        return flightService.getAll(flight -> flight.getDepartureTime().equals(date));
    }

    @Override
    public void cancelFlight(int flightId) {
        flightService.cancelFlight(flightId);
    }
}

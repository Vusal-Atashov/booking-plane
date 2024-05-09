package controller;

import dto.FlightDto;
import services.FlightService;

import java.time.LocalDateTime;
import java.util.List;

public class FlightControllerImpl implements FlightController {
    private final FlightService flightService;

    public FlightControllerImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public void saveAllToFile() {
        flightService.saveAllToFile();
    }

    @Override
    public void getAllFromFile() {
        flightService.getAllFromFile();
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @Override
    public FlightDto getFlightById(long flightId) {
        return flightService.getFlightById(flightId);
    }

    @Override
    public void saveFlight(FlightDto flightDto) {
        flightService.saveFlight(flightDto);
    }

    @Override
    public List<FlightDto> getFlightsByCriteria(String destination, LocalDateTime dateTime, int seats) {
        return flightService.getFlightsByCriteria(destination, dateTime, seats);
    }
}
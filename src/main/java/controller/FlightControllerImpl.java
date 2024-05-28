package controller;

import dao.entity.Cities;
import dto.CriteriaDto;
import dto.FlightDto;
import services.FlightService;

import java.util.List;

public class FlightControllerImpl implements FlightController {
    private final FlightService flightService;

    public FlightControllerImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public List<FlightDto> findByOrigin(String origin) {
        return flightService.findByOrigin(origin);
    }

    @Override
    public void cancelFlight(int flightId) {
        flightService.cancelFlight(flightId);
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
    public List<FlightDto> getFlightsByCriteria(CriteriaDto criteria) {
        return flightService.getFlightsByCriteria(criteria);
    }

    @Override
    public List<FlightDto> getNext24HoursFlights(Cities origin) {
        return flightService.getNext24HoursFlights(origin);
    }
}

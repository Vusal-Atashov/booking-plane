package services;

import dao.FlightDao;
import dao.FlightsDao;
import dao.entity.FlightEntity;
import dto.FlightDto;
import exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {
    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public void saveAllToFile() {
        flightDao.saveAllToFile();
    }

    @Override
    public void getAllFromFile() {
        flightDao.getAllFromFile();
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightDao.getAll().stream()
                .map(flight -> new FlightDto(flight.getId(), flight.getOrigin(),
                        flight.getDestination(), flight.getDepartureTime(),
                        flight.getNumOfSeats()))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto getFlightById(long flightId) {
        FlightEntity flight = flightDao.getById(flightId).
                orElseThrow(() -> new ResourceNotFoundException("flight-not-found"));
        return new FlightDto(flight.getId(), flight.getOrigin(),
                flight.getDestination(), flight.getDepartureTime(),
                flight.getNumOfSeats());
    }

    @Override
    public List<FlightDto> getFlightsByCriteria(String destination, LocalDateTime dateTime, int seats) {
        List<FlightEntity> entities = flightDao.getAll();
        var flightDtos = entities.stream().filter(entity ->
                entity.getDestination().equals(destination) &&
                        entity.getDepartureTime().equals(dateTime) &&
                        entity.getNumOfSeats() >= seats
        ).map(entity -> new FlightDto(entity.getId(), entity.getOrigin(),
                        entity.getDestination(), entity.getDepartureTime(),
                        entity.getNumOfSeats())
        ).toList();
        return flightDtos;
    }

    @Override
    public void saveFlight(FlightDto flightDto) {
        FlightEntity flightEntity = new FlightEntity(flightDto.getOrigin(),
                flightDto.getDestination(), flightDto.getDepartureTime(),
                flightDto.getNumOfSeats());
        flightDao.save(flightEntity);
    }
}

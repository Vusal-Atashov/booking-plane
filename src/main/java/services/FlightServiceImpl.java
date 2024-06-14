package services;

import dao.FlightDao;
import dao.entity.Cities;
import dao.entity.FlightEntity;
import dto.CriteriaDto;
import dto.FlightDto;
import exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {
    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public void saveFlight(FlightDto flightDto) {
        FlightEntity flightEntity = new FlightEntity(flightDto.getOrigin(),
                flightDto.getDestination(), flightDto.getDepartureTime(),
                flightDto.getNumOfSeats());
        flightDao.save(flightEntity);
    }

    @Override
    public void cancelFlight(int flightId) {
        flightDao.cancelFlight(flightId);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(flight.getId(), flight.getOrigin(),
                        flight.getDestination(), flight.getDepartureTime(),
                        flight.getNumOfSeats()))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto getFlightById(long flightId) {
        FlightEntity flightEntity = flightDao.findById(flightId);
        if (flightEntity == null) {
            throw new ResourceNotFoundException("Flight not found");
        }
        return new FlightDto(flightEntity.getId(), flightEntity.getOrigin(),
                flightEntity.getDestination(), flightEntity.getDepartureTime(),
                flightEntity.getNumOfSeats());
    }

    @Override
    public List<FlightDto> findByOrigin(String origin) {
        return flightDao.findByOrigin(origin).stream()
                .map(flight -> new FlightDto(flight.getId(), flight.getOrigin(),
                        flight.getDestination(), flight.getDepartureTime(),
                        flight.getNumOfSeats()))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> getFlightsByCriteria(CriteriaDto criteria) {
        List<FlightEntity> entities = flightDao.findAll();
        try {
            return entities.stream().filter(entity ->
                    entity.getDestination().equals(criteria.getDestination()) &&
                            entity.getDepartureTime().equals(criteria.getTime()) &&
                            entity.getNumOfSeats() >= criteria.getSeats()
            ).map(entity -> new FlightDto(entity.getId(), entity.getOrigin(),
                    entity.getDestination(), entity.getDepartureTime(),
                    entity.getNumOfSeats())
            ).collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

    }

    @Override
    public List<FlightDto> getNext24HoursFlights(Cities origin) {
        try {
            List<FlightEntity> entities = flightDao.findAll();
            return entities.stream().filter(entity ->
                    entity.getOrigin().equals(origin) &&
                            entity.getDepartureTime().isAfter(LocalDateTime.now()) &&
                            entity.getDepartureTime().isBefore(LocalDateTime.now().plusHours(24))
            ).map(entity -> new FlightDto(entity.getId(), entity.getOrigin(),
                    entity.getDestination(), entity.getDepartureTime(),
                    entity.getNumOfSeats())
            ).collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}

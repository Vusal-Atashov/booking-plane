package services;

import dao.FlightsEntity;
import dao.FlightsDao;
import dto.FlightDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightServiceIMPL implements FlightService {

    private final FlightsDao flightsDao;

    public FlightServiceIMPL(FlightsDao flightsDao) {
        this.flightsDao = flightsDao;
    }

    @Override
    public void saveFlights(Collection<FlightDTO> flights) {
        flightsDao.save(flights.stream()
                .map(dto -> new FlightsEntity(dto.getDestination(), dto.getDate(), dto.getNumOfSeats()))
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<FlightDTO> getAllFlights() {
        return flightsDao.getAll().stream()
                .map(flight -> new FlightDTO(flight.getId(),flight.getDestination(), flight.getDate(), flight.getNumOfSeats()))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelFlight(int flightId) {
        flightsDao.delete(flightId);
    }


    @Override
    public List<FlightDTO> getAll(Predicate<FlightsEntity> predicate) {
        return flightsDao.getAllBy(predicate)
                .orElse(new ArrayList<>())
                .stream()
                .map(entity -> new FlightDTO(entity.getId(), entity.getDestination(), entity.getDate(), entity.getNumOfSeats()))
                .toList();
    }

    @Override
    public FlightDTO getOneFlightBy(Predicate<FlightsEntity> predicate) {
        return flightsDao.getOneBy(predicate)
                .map(entity -> new FlightDTO(entity.getId(), entity.getDestination(), entity.getDate(), entity.getNumOfSeats()))
                .orElse(null);

    }
}

package services;

import dao.entity.FlightEntity;
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
                .map(dto -> new FlightEntity(dto.getOrigin(), dto.getDestination(),
                        dto.getDepartureTime(), dto.getNumOfSeats()))
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<FlightDTO> getAllFlights() {
        return flightsDao.getAll().stream()
                .map(flight -> new FlightDTO(flight.getId(), flight.getOrigin(),
                        flight.getDestination(), flight.getDepartureTime(),
                        flight.getNumOfSeats()))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelFlight(int flightId) {
        flightsDao.delete(flightId);
    }


    @Override
    public List<FlightDTO> getAll(Predicate<FlightEntity> predicate) {
        return flightsDao.getAllBy(predicate)
                .orElse(new ArrayList<>())
                .stream()
                .map(entity -> new FlightDTO(entity.getId(), entity.getOrigin(),
                        entity.getDestination(), entity.getDepartureTime(),
                        entity.getNumOfSeats()))
                .toList();
    }

    @Override
    public FlightDTO getOneFlightBy(Predicate<FlightEntity> predicate) {
        return flightsDao.getOneBy(predicate)
                .map(entity -> new FlightDTO(entity.getId(), entity.getOrigin(),
                        entity.getDestination(), entity.getDepartureTime(),
                        entity.getNumOfSeats()))
                .orElse(null);

    }
}

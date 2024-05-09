package services;

import dao.entity.FlightEntity;
import dto.FlightDTO;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface FlightService {
    void saveFlights(Collection<FlightDTO> flights);

    Collection<FlightDTO> getAllFlights();

    void cancelFlight(int flightId);

    List<FlightDTO> getAll(Predicate<FlightEntity> predicate);

    FlightDTO getOneFlightBy(Predicate<FlightEntity> predicate);
}

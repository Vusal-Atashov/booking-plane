package services;

import dao.FlightsEntity;
import dto.FlightDTO;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface FlightService {
    void saveFlights(Collection<FlightDTO> flights);

    Collection<FlightDTO> getAllFlights();

    void cancelFlight(int flightId);

    List<FlightDTO> getAll(Predicate<FlightsEntity> predicate);

    FlightDTO getOneFlightBy(Predicate<FlightsEntity> predicate);
}

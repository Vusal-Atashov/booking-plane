package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FlightsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

public class FlightsFileDAOTest {
    private FlightsFileDAO flightsFileDAO;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        flightsFileDAO = new FlightsFileDAO(objectMapper);
    }

    @Test
    void saveAndGetAllTest() {
        FlightsEntity flight1 = new FlightsEntity(1, "Destination 1", "2024-05-10", 100);
        FlightsEntity flight2 = new FlightsEntity(2, "Destination 2", "2024-05-11", 120);
        assertTrue(flightsFileDAO.save(Arrays.asList(flight1, flight2)));
        Collection<FlightsEntity> allFlights = flightsFileDAO.getAll();
        assertEquals(2, allFlights.size());
        assertTrue(allFlights.contains(flight1));
        assertTrue(allFlights.contains(flight2));
    }

    @Test
    void getOneByTest() {
        Predicate<FlightsEntity> predicate = flight -> flight.getId() == 1;
        Optional<FlightsEntity> flightOptional = flightsFileDAO.getOneBy(predicate);
        assertTrue(flightOptional.isPresent());
        assertEquals(1, flightOptional.get().getId());
    }

    @Test
    void getAllByTest() {
        Predicate<FlightsEntity> predicate = flight -> flight.getNumOfSeats() > 100;
        Optional<Collection<FlightsEntity>> flightsOptional = flightsFileDAO.getAllBy(predicate);
        assertTrue(flightsOptional.isPresent());
        assertEquals(1, flightsOptional.get().size());
        assertEquals(120, flightsOptional.get().iterator().next().getNumOfSeats());
    }

    @Test
    void deleteTest() {
        flightsFileDAO.delete(1);
        Optional<FlightsEntity> deletedFlightOptional = flightsFileDAO.getOneBy(flight -> flight.getId() == 1);
        assertFalse(deletedFlightOptional.isPresent());
    }
}

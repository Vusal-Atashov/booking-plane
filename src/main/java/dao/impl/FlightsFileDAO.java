package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BookingEntity;
import dao.FlightsDao;
import dao.FlightsEntity;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class FlightsFileDAO extends FlightsDao {
    private static final String RESOURCE_PATH = "src/main/java/resource/";
    private static final String FLIGHTS_FILE_PATH = RESOURCE_PATH + "Flights.json";
    private final ObjectMapper objectMapper;

    public FlightsFileDAO(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean save(Collection<FlightsEntity> flights) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FLIGHTS_FILE_PATH))) {
            bw.write(objectMapper.writeValueAsString(flights));
            return true;
        } catch (IOException e) {
            System.err.println("Error while saving flights: " + e.getMessage());
            return false;
        }
    }
    public Collection<FlightsEntity> getAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(FLIGHTS_FILE_PATH))) {
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(br, FlightsEntity[].class)));
        } catch (IOException e) {
            System.err.println("Error while reading flights from file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<FlightsEntity> getOneBy(Predicate<FlightsEntity> predicate) {
        return getAll().stream().filter(predicate).findFirst();
    }

    @Override
    public Optional<Collection<FlightsEntity>> getAllBy(Predicate<FlightsEntity> predicate) {
        return Optional.of(getAll().stream().filter(predicate).toList());
    }

    @Override
    public void delete(int id) {
        Collection<FlightsEntity> bookings = getAll();
        bookings.removeIf(flights -> flights.getId() == id);
        save(bookings);
    }
}


package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.entity.FlightEntity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FlightDaoImpl implements FlightDao {
    private static final String RESOURCE_PATH = "src/main/java/resource/";
    private static final String FLIGHTS_FILE_PATH = RESOURCE_PATH + "flights.json";
    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final List<FlightEntity> flightEntityList = new ArrayList<>();
    @Override
    public void save(FlightEntity entity) {
        flightEntityList.add(entity);
    }

    @Override
    public void saveAll(List<FlightEntity> entities) {
        flightEntityList.addAll(entities);
    }

    @Override
    public Optional<FlightEntity> getById(long id) {
        return flightEntityList.stream().filter(entity -> entity.getId() == id).findFirst();
    }

    @Override
    public List<FlightEntity> getAll() {
        return flightEntityList;
    }

    @Override
    public List<FlightEntity> getAllFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FLIGHTS_FILE_PATH))) {
            flightEntityList.addAll(Arrays.asList(MAPPER.readValue(br, FlightEntity[].class)));
            return flightEntityList;
        } catch (IOException e) {
            System.err.println("Error while reading flights from file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void saveAllToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FLIGHTS_FILE_PATH))) {
            bw.write(MAPPER.writeValueAsString(flightEntityList));
        } catch (IOException e) {
            System.err.println("Error while saving flights: " + e.getMessage());
        }
    }
}

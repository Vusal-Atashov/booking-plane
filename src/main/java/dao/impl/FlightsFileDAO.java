package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FlightsDao;
import dao.FlightsEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class FlightsFileDAO extends FlightsDao {
    private static final String RESOURCE_PATH = "src/main/java/resource/";
    private static final String FLIGHT_FILE_PATH = RESOURCE_PATH.concat("flights");
    private final ObjectMapper objectMapper;

    public FlightsFileDAO(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public FlightsEntity save(FlightsEntity flightsEntity) {
        return null;
    }

    @Override
    public Collection<FlightsEntity> getAll() {
        return null;
    }

    @Override
    public Optional<FlightsEntity> getOneBy(Predicate<FlightsEntity> predicate) {
        return Optional.empty();
    }

    @Override
    public Collection<FlightsEntity> getAllBY(Predicate<FlightsEntity> predicate) {
        return null;
    }

}

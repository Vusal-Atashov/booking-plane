package dao;

import dao.entity.FlightEntity;

import java.util.List;
import java.util.Optional;

public interface FlightDao {
    void save(FlightEntity entity);

    void saveAll(List<FlightEntity> entities);

    Optional<FlightEntity> getById(long id);

    List<FlightEntity> getAll();

    List<FlightEntity> getAllFromFile();

    void saveAllToFile();
}

package dao;

import dao.entity.FlightEntity;

import java.util.List;

public interface FlightDao {
    void save(FlightEntity entity);

    FlightEntity findById(long id);

    List<FlightEntity> findAll();

    void cancelFlight(long flightId);

    List<FlightEntity> findByOrigin(String origin);
}

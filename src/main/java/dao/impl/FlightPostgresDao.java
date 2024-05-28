package dao.impl;

import dao.FlightDao;
import dao.entity.FlightEntity;

import java.util.List;

public class FlightPostgresDao implements FlightDao {

   /* private static final String getAllFlightSql ="Select  from flight ";
    private static final String ="";
    private static final String ="";
    private static final String ="";
    private static final String ="";
    private static final String ="";
    private static final String ="";
*/
    public FlightPostgresDao() {

    }

    @Override
    public void save(FlightEntity entity) {

    }

    @Override
    public FlightEntity findById(long id) {
        return null;
    }

    @Override
    public List<FlightEntity> findAll() {
        return null;
    }

    @Override
    public void cancelFlight(long flightId) {

    }

    @Override
    public List<FlightEntity> findByOrigin(String origin) {
        return null;
    }
}

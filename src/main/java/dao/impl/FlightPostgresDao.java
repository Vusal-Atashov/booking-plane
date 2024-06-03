package dao.impl;

import dao.FlightDao;
import dao.entity.Cities;
import dao.entity.FlightEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightPostgresDao implements FlightDao {
    @Override
    public void save(FlightEntity entity) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("INSERT INTO flight (origin, destination, departure_time, " +
                     "free_seats) VALUES (?, ?, ?, ?)")) {
            query.setString(1, entity.getOrigin().name());
            query.setString(2, entity.getDestination().name());
            query.setTimestamp(3, java.sql.Timestamp.valueOf(entity.getDepartureTime()));
            query.setInt(4, entity.getNumOfSeats());
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public FlightEntity findById(long id) {
        FlightEntity flightEntity = null;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("SELECT * FROM flight WHERE id = ?;")) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long flightId = resultSet.getLong("id");
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flightEntity = new FlightEntity(flightId, Cities.valueOf(origin), Cities.valueOf(destination), departureTime, numOfSeats);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flightEntity;
    }

    @Override
    public List<FlightEntity> findAll() {
        List<FlightEntity> flightEntities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("SELECT * FROM flight;")) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long flightId = resultSet.getLong("id");
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flightEntities.add(new FlightEntity(flightId, Cities.valueOf(origin), Cities.valueOf(destination), departureTime, numOfSeats));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flightEntities;
    }

    @Override
    public void cancelFlight(long flightId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("DELETE FROM flight WHERE id = ?;")) {
            query.setLong(1, flightId);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<FlightEntity> findByOrigin(String origin) {
        List<FlightEntity> flightEntities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("SELECT * FROM flight WHERE origin = ?;")) {
            query.setString(1, origin.toUpperCase());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long flightId = resultSet.getLong("id");
                String originCity = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                int numOfSeats = resultSet.getInt("free_seats");
                flightEntities.add(new FlightEntity(flightId, Cities.valueOf(originCity), Cities.valueOf(destination), departureTime, numOfSeats));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flightEntities;
    }
}
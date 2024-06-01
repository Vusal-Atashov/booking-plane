package dao.impl;

import dao.BookingDao;
import dao.entity.BookingEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingPostgresDao implements BookingDao {
    private static final String getAllBookingSql =
            "SELECT booking.id, booking.flight_id, full_name FROM booking JOIN bookings_passengers ON booking.id =bookings_passengers.booking_id JOIN passengerON passenger.id =bookings_passengers.passenger_id;";
    private static final String getBookingByIdSql = "SELECT * FROM booking WHERE id = ?;";
    private static final String createBookingSql = "INSERT INTO booking (flight_id) VALUES (?);";
    private static final String createPassengerNameSql = "INSERT INTO passenger (full_name) VALUES (?);";
    private static final String createBookingByPassengerIdSql = "INSERT INTO bookings_passengers (booking_id, passenger_id) VALUES (?, ?);";
    private static final String deleteBookingSql = "DELETE FROM booking where id = ?";
    private static final String deletePassengerNameSql = "DELETE FROM passengername WHERE bookingId = ?;";
    private static final String updateFlightSql = "UPDATE flight SET free_seats = ? WHERE id = ?;";


    @Override
    public List<BookingEntity> findByFullName(List<String> passengerNames) {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement(getAllBookingSql)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long flightId = resultSet.getLong("flightid");
                String passengerName = resultSet.getString("passenger_name");
                if (passengerNames.contains(passengerName)) {
                    bookingEntities.add(new BookingEntity(id, flightId, List.of(passengerName.split(","))));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingEntities;
    }

    @Override
    public List<BookingEntity> findAll() {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement(getAllBookingSql)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long flightId = resultSet.getLong("flightid");
                String passengerName = resultSet.getString("passenger_name");
                bookingEntities.add(new BookingEntity(id, flightId, List.of(passengerName.split(","))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingEntities;
    }

    @Override
    public void save(BookingEntity entity) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement(createBookingSql)) {
            PreparedStatement updateFlightQuery = conn.prepareStatement(updateFlightSql);
            updateFlightQuery.setInt(1, entity.getPassengerNames().size());
            updateFlightQuery.setLong(2, entity.getFlightId());
            updateFlightQuery.executeUpdate();
            query.setLong(1, entity.getFlightId());
            query.executeUpdate();
            PreparedStatement queryName = conn.prepareStatement(createPassengerNameSql);
            for (String name : entity.getPassengerNames()) {
                queryName.setString(1, name);
                queryName.executeUpdate();
                PreparedStatement queryBooking = conn.prepareStatement(createBookingByPassengerIdSql);
                queryBooking.setLong(1, entity.getId());
                queryBooking.setLong(2, entity.getFlightId());
                queryBooking.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<BookingEntity> findById(long id) {
        BookingEntity bookingEntity = null;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement(getBookingByIdSql)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long bookingId = resultSet.getLong("id");
                long flightId = resultSet.getLong("flightid");
                String passengerName = resultSet.getString("passenger_name");
                bookingEntity = new BookingEntity(bookingId, flightId, List.of(passengerName.split(",")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(bookingEntity);
    }

    @Override
    public void cancelBooking(long bookingId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement(deleteBookingSql)) {
            query.setLong(1, bookingId);
            query.executeUpdate();
            PreparedStatement queryName = conn.prepareStatement(deletePassengerNameSql);
            queryName.setLong(1, bookingId);
            queryName.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

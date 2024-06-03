package dao.impl;

import dao.BookingDao;
import dao.entity.BookingEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingPostgresDao implements BookingDao {

    @Override
    public void save(BookingEntity entity) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("INSERT INTO booking (flight_id) VALUES (?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, entity.getFlightId());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                long bookingId = generatedKeys.getLong(1);
                for (String passengerName : entity.getPassengerNames()) {
                    PreparedStatement queryPassenger = conn.prepareStatement("INSERT INTO passenger (full_name) " +
                            "VALUES (?);", Statement.RETURN_GENERATED_KEYS);
                    queryPassenger.setString(1, passengerName);
                    queryPassenger.executeUpdate();
                    ResultSet passengerKeys = queryPassenger.getGeneratedKeys();
                    if (passengerKeys.next()) {
                        long passengerId = passengerKeys.getLong(1);
                        PreparedStatement queryBookingPassenger = conn.prepareStatement(
                                "INSERT INTO bookings_passengers (booking_id, passenger_id) VALUES (?, ?);");
                        queryBookingPassenger.setLong(1, bookingId);
                        queryBookingPassenger.setLong(2, passengerId);
                        queryBookingPassenger.executeUpdate();
                    }
                }
                PreparedStatement queryFlight = conn.prepareStatement("UPDATE flight SET free_seats = free_seats - (SELECT COUNT(*) FROM bookings_passengers WHERE booking_id = ?) WHERE id = ?;");
                queryFlight.setLong(1, bookingId);
                queryFlight.setLong(2, entity.getFlightId());
                queryFlight.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<BookingEntity> findAll() {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("SELECT booking.id, booking.flight_id, " +
                     "string_agg(passenger.full_name, ',') as passenger_name FROM booking " +
                     "JOIN bookings_passengers ON booking.id = bookings_passengers.booking_id " +
                     "JOIN passenger ON bookings_passengers.passenger_id = passenger.id GROUP BY booking.id;")) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long flightId = resultSet.getLong("flight_id");
                String passengerName = resultSet.getString("passenger_name");
                bookingEntities.add(new BookingEntity(id, flightId, List.of(passengerName.split(","))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingEntities;
    }

    @Override
    public void cancelBooking(long bookingId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres")) {
            PreparedStatement queryFlight = conn.prepareStatement("UPDATE flight SET free_seats = free_seats + " +
                    "(SELECT COUNT(*) FROM bookings_passengers WHERE booking_id = ?) WHERE id = ?;");
            queryFlight.setLong(1, bookingId);
            queryFlight.setLong(2, findById(bookingId).get().getFlightId());
            queryFlight.executeUpdate();
            PreparedStatement query = conn.prepareStatement("DELETE FROM bookings_passengers WHERE booking_id = ?;");
            query.setLong(1, bookingId);
            query.executeUpdate();
            PreparedStatement queryBooking = conn.prepareStatement("DELETE FROM booking WHERE id = ?;");
            queryBooking.setLong(1, bookingId);
            queryBooking.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<BookingEntity> findByFullName(List<String> passengerNames) {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("SELECT booking.id, booking.flight_id, " +
                     "string_agg(passenger.full_name, ',') as passenger_name FROM booking " +
                     "JOIN bookings_passengers ON booking.id = bookings_passengers.booking_id " +
                     "JOIN passenger ON bookings_passengers.passenger_id = passenger.id GROUP BY booking.id;")) {
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
    public Optional<BookingEntity> findById(long id) {
        BookingEntity bookingEntity = null;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
             PreparedStatement query = conn.prepareStatement("SELECT * FROM booking WHERE id = ?;")) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long bookingId = resultSet.getLong("id");
                long flightId = resultSet.getLong("flight_id");
                bookingEntity = new BookingEntity(bookingId, flightId, new ArrayList<>());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(bookingEntity);

    }
}
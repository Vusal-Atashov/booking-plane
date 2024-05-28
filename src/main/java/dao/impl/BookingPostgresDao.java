package dao.impl;

import dao.BookingDao;
import dao.ConnectionDataBase;
import dao.entity.BookingEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingPostgresDao implements BookingDao {
    private static final String getAllBookingSql =
            "SELECT booking.id, booking.flightId, passenger_name FROM booking LEFT JOIN passengerName pN ON booking.id = pN.bookingId;";
    private static final String getBookingByIdSql = "SELECT * FROM booking WHERE id = ?;";
    private static final String createBookingSql = "INSERT INTO booking(flightid) values (?);";
    private static final String createBookingByPassengerNameSql = "INSERT INTO passengername(bookingId,passenger_name) values (?,?);";
    private static final String deleteBookingSql = "DELETE FROM booking where id = ?";
    private static final String deletePassengerNameSql = "DELETE FROM passengername WHERE bookingId = ?;";
    private static final ConnectionDataBase connection = new ConnectionDataBase();

    @Override
    public List<BookingEntity> findByFullName(List<String> passengerNames) {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (PreparedStatement query = connection.getConnection().prepareStatement(getAllBookingSql)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long flightId = resultSet.getLong("flightid");
                String passengerName = resultSet.getString("passenger_name");
                if (passengerNames.contains(passengerName)) {
                    bookingEntities.add(new BookingEntity(id, flightId, List.of(passengerName.split(","))));
                }
            }
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingEntities;
    }

    @Override
    public List<BookingEntity> findAll() {
        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (PreparedStatement query = connection.getConnection().prepareStatement(getAllBookingSql)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long flightId = resultSet.getLong("flightid");
                String passengerNames = resultSet.getString("passenger_name");
                bookingEntities.add(new BookingEntity(id, flightId, List.of(passengerNames.split(","))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingEntities;
    }

    @Override
    public void save(BookingEntity entity) {
        try (PreparedStatement query = connection.getConnection().prepareStatement(createBookingSql);
             PreparedStatement queryName = connection.getConnection().prepareStatement(createBookingByPassengerNameSql);) {
            for (String passengerName : entity.getPassengerNames()) {
                queryName.setLong(1, entity.getFlightId());
                queryName.setString(2, passengerName);
                queryName.executeUpdate();
            }
            query.setLong(1, entity.getFlightId());
            int affectedRows = query.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<BookingEntity> findById(long id) {
        try (PreparedStatement query = connection.getConnection().prepareStatement(getBookingByIdSql);) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                long id2 = resultSet.getLong("id");
                long flightId = resultSet.getLong("flightid");
                String passengerNames = resultSet.getString("passengernames");
                return Optional.of(new BookingEntity(id2, flightId, List.of(passengerNames.split(","))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void cancelBooking(long bookingId) {
        try (PreparedStatement query = connection.getConnection().prepareStatement(deleteBookingSql);
             PreparedStatement queryName = connection.getConnection().prepareStatement(deletePassengerNameSql);) {
            query.setLong(1, bookingId);
            queryName.setLong(1, bookingId);
            queryName.executeUpdate();
            int affectedRows = query.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

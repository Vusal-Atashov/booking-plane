package dao;

import dao.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface BookingDao {
    void save(BookingEntity entity);

    Optional<BookingEntity> findById(long id);

    List<BookingEntity> findByFullName(List<String> passengerNames);

    List<BookingEntity> findAll();

    void cancelBooking(long bookingId);
}

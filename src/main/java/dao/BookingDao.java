package dao;

import dao.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface BookingDao{
    void save(BookingEntity entity);

    void saveAll(List<BookingEntity> entities);

    Optional<BookingEntity> getById(long id);
    List<BookingEntity> getByFullName(List<String> passengerNames);

    List<BookingEntity> getAll();

    List<BookingEntity> getAllFromFile();

    void saveAllToFile();
    void cancelBooking(int bookingId);
}

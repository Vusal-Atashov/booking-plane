package services;

import dao.entity.BookingEntity;
import dto.BookingDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);
    Optional<BookingEntity> findById(long id);


    //    void saveAllToFile();
    List<BookingDto> getAllBookings();

    //    void getAllFromFile();
    void cancelBooking(int bookingId);

    List<BookingDto> getAllBookingsByPassenger(String passengerNames);
}

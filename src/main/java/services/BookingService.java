package services;

import dao.entity.BookingEntity;
import dto.BookingDTO;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface BookingService {
    void saveBookings(Collection<BookingDTO> bookings);
    Collection<BookingDTO> getBookingsAll();
    void cancelBooking(int bookingId);
    List<BookingDTO> getAllBookingsByPassenger(Predicate<BookingEntity> predicate);
    BookingDTO getOneBookingsBy(Predicate<BookingEntity> predicate);

}

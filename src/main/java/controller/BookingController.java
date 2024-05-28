package controller;

import dao.entity.BookingEntity;
import dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingController {
    void saveBooking(BookingDto bookingDto);

    List<BookingDto> getAllBookings();

    Optional<BookingEntity> findById(long id);

    List<BookingDto> getAllBookingsByPassenger(String passengerName);

    void cancelBooking(int bookingId);

}


package controller;

import dto.BookingDto;
import dto.FlightDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface BookingController {
    void saveBooking(BookingDto bookingDto);
    void saveAllToFile();

    List<BookingDto> getAllBookings();
    void getAllFromFile();

    List<BookingDto> getAllBookingsByPassenger(String passengerName);
    void cancelBooking(int bookingId);
}


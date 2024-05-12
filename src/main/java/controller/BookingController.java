package controller;

import dto.BookingDto;

import java.util.List;

public interface BookingController {
    void saveBooking(BookingDto bookingDto);
    void saveAllToFile();
    List<BookingDto> getAllBookings();
    void getAllFromFile();
    List<BookingDto> getAllBookingsByPassenger(String passengerName);
    void cancelBooking(int bookingId);
}


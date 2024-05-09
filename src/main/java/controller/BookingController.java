package controller;

import dto.BookingDTO;

import java.util.Collection;
import java.util.List;


public interface BookingController {
    void saveBooking(Collection<BookingDTO> bookingDTOS);
    Collection<BookingDTO> getAllBooking();
    BookingDTO getOneBookings(int id);
    List<BookingDTO> getAllBookingsByPassenger(List<String> passengerNames);
    List<BookingDTO> getAllBookingsByPassenger(int flightId);
    void removeBooking(int id);
}


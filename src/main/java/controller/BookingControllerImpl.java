package controller;

import dto.BookingDto;
import services.BookingService;
import services.BookingServiceImpl;

import java.util.List;

public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService = new BookingServiceImpl();

    @Override
    public void saveBooking(BookingDto bookingDto) {
        bookingService.saveBooking(bookingDto);
    }

    @Override
    public void saveAllToFile() {
        bookingService.saveAllToFile();
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @Override
    public void getAllFromFile() {
        bookingService.getAllFromFile();
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDto> getAllBookingsByPassenger(String passengerName) {
        return bookingService.getAllBookingsByPassenger(passengerName);
    }

}

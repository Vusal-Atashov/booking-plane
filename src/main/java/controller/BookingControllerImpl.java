package controller;

import dao.entity.BookingEntity;
import dto.BookingDto;
import services.BookingService;

import java.util.List;
import java.util.Optional;

public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;

    public BookingControllerImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void saveBooking(BookingDto bookingDto) {
        bookingService.saveBooking(bookingDto);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @Override
    public Optional<BookingEntity> findById(long id) {
        return bookingService.findById(id);
    }

    @Override
    public List<BookingDto> getAllBookingsByPassenger(String passengerName) {
        return bookingService.getAllBookingsByPassenger(passengerName);
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}

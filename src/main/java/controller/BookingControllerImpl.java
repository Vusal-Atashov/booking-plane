package controller;

import dto.BookingDTO;
import services.BookingService;

import java.util.Collection;
import java.util.List;

public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;

    public BookingControllerImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @Override
    public void saveBooking(Collection<BookingDTO> bookingDTOS) {
        bookingService.saveBookings(bookingDTOS);
    }

    @Override
    public Collection<BookingDTO> getAllBooking() {
        return bookingService.getBookingsAll();
    }

    @Override
    public BookingDTO getOneBookings(int id) {
        return bookingService.getOneBookingsBy(booking -> booking.getId() == (id));
    }

    @Override
    public List<BookingDTO> getAllBookingsByPassenger(List<String> passengerNames) {
        return bookingService.getAllBookingsByPassenger(booking -> booking.getPassengerNames().equals(passengerNames));
    }

    @Override
    public List<BookingDTO> getAllBookingsByPassenger(int flightId) {
        return bookingService.getAllBookingsByPassenger(booking -> booking.getFlightId() == (flightId));
    }

    @Override
    public void removeBooking(int id) {
        bookingService.cancelBooking(id);
    }
}

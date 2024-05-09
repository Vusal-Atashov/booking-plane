package services;

import dao.BookingDao;
import dao.entity.BookingEntity;
import dto.BookingDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void saveBookings(Collection<BookingDTO> bookings) {
        bookingDao.save(bookings.stream()
                .map(dto -> new BookingEntity(dto.getFlightId(), dto.getPassengerNames()))
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<BookingDTO> getBookingsAll() {
        return bookingDao.getAll().stream()
                .map(booking -> new BookingDTO(booking.getId(), booking.getFlightId(), booking.getPassengerNames()))
                .collect(Collectors.toList());
    }


    @Override
    public void cancelBooking(int bookingId) {
        bookingDao.delete(bookingId);
    }


    @Override
    public List<BookingDTO> getAllBookingsByPassenger(Predicate<BookingEntity> predicate) {
        return bookingDao.getAllBy(predicate)
                .orElse(new ArrayList<>())
                .stream()
                .map(entity -> new BookingDTO(entity.getId(), entity.getFlightId(), entity.getPassengerNames()))
                .toList();
    }

    @Override
    public BookingDTO getOneBookingsBy(Predicate<BookingEntity> predicate) {
        return bookingDao.getOneBy(predicate)
                .map(entity -> new BookingDTO(entity.getId(), entity.getFlightId(), entity.getPassengerNames()))
                .orElse(null);

    }


}


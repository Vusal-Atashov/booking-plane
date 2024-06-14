package services;

import dao.BookingDao;
import dao.entity.BookingEntity;
import dto.BookingDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void saveBooking(BookingDto bookingDto) {
        BookingEntity bookingEntity = new BookingEntity(bookingDto.getFlightId(), bookingDto.getPassengerNames());
        bookingDao.save(bookingEntity);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingDao.findAll().stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getFlightId(),
                        booking.getPassengerNames()))
                .collect(Collectors.toList());
    }

    public Optional<BookingEntity> findById(long id) {
        return bookingDao.findById(id);
    }

    @Override
    public List<BookingDto> getAllBookingsByPassenger(String passengerName) {
        return bookingDao.findByFullName(List.of(passengerName)).stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getFlightId(),
                        booking.getPassengerNames()))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingDao.cancelBooking(bookingId);
    }
}
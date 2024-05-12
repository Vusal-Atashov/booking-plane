package services;

import dao.BookingDao;
import dao.entity.BookingEntity;
import dao.impl.BookingDaoImpl;
import dto.BookingDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao = new BookingDaoImpl();

    @Override
    public void saveBooking(BookingDto bookingDto) {
        BookingEntity bookingEntity = new BookingEntity(bookingDto.getId(),
                bookingDto.getFlightId(), bookingDto.getPassengerNames());
        bookingDao.save(bookingEntity);
    }

    @Override
    public void saveAllToFile() {
        bookingDao.saveAllToFile();
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingDao.getAll().stream()
                .map(booking -> new BookingDto(booking.getId(), booking.getFlightId(),
                        booking.getPassengerNames()))
                .collect(Collectors.toList());
    }

    @Override
    public void getAllFromFile() {
        bookingDao.getAllFromFile();
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingDao.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDto> getAllBookingsByPassenger(String passengerName) {
        List<BookingEntity> entities = bookingDao.getAll();
        var bookingDtos = entities.stream().filter(entity ->
                entity.getPassengerNames().contains(passengerName)
        ).map(entity -> new BookingDto(entity.getId(),
                entity.getFlightId(), entity.getPassengerNames())
        ).toList();
        return bookingDtos;
    }
}
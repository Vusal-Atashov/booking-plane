package dao.impl;

import dao.entity.BookingEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingFileDaoTest {


    @org.junit.jupiter.api.Test
    void saveBooking() {
        // Arrange
        BookingFileDao bookingFileDao = new BookingFileDao();
        List<String> passengerNames = Arrays.asList("John Doe", "Jane Doe");
        BookingEntity bookingEntity = new BookingEntity(1L, 1L, passengerNames);

        // Act
        bookingFileDao.save(bookingEntity);
        Optional<BookingEntity> savedBookingEntityOptional = bookingFileDao.findById(1L);

        // Assert
        assertTrue(savedBookingEntityOptional.isPresent(), "Booking should be saved");
        BookingEntity savedBookingEntity = savedBookingEntityOptional.get();
        assertEquals(bookingEntity.getId(), savedBookingEntity.getId(), "Saved booking ID should match");
        assertEquals(bookingEntity.getFlightId(), savedBookingEntity.getFlightId(), "Saved flight ID should match");
        assertEquals(new HashSet<>(bookingEntity.getPassengerNames()), new HashSet<>(savedBookingEntity.getPassengerNames()), "Saved passenger names should match");
    }
    @org.junit.jupiter.api.Test
    void findById() {
        // Arrange
        BookingFileDao bookingFileDao = new BookingFileDao();
        List<String> passengerNames = Arrays.asList("John Doe", "Jane Doe");
        BookingEntity bookingEntity = new BookingEntity(1L, 1L, passengerNames);
        bookingFileDao.save(bookingEntity);

        // Act
        Optional<BookingEntity> foundBookingEntityOptional = bookingFileDao.findById(1L);

        // Assert
        assertTrue(foundBookingEntityOptional.isPresent(), "Booking should be found");
        BookingEntity foundBookingEntity = foundBookingEntityOptional.get();
        assertEquals(bookingEntity.getId(), foundBookingEntity.getId(), "Found booking ID should match");
        assertEquals(bookingEntity.getFlightId(), foundBookingEntity.getFlightId(), "Found flight ID should match");
        assertEquals(new HashSet<>(bookingEntity.getPassengerNames()), new HashSet<>(foundBookingEntity.getPassengerNames()), "Found passenger names should match");
    }

    @org.junit.jupiter.api.Test
    void getAllBookings() {
        // Arrange
        BookingFileDao bookingFileDao = new BookingFileDao();
        List<String> passengerNames1 = Arrays.asList("John Doe", "Jane Doe");
        List<String> passengerNames2 = Arrays.asList("Alice", "Bob");
        BookingEntity bookingEntity1 = new BookingEntity(1L, 1L, passengerNames1);
        BookingEntity bookingEntity2 = new BookingEntity(2L, 2L, passengerNames2);
        bookingFileDao.save(bookingEntity1);
        bookingFileDao.save(bookingEntity2);

        // Act
        List<BookingEntity> allBookings = bookingFileDao.findAll();

        // Assert
        assertNotNull(allBookings, "Bookings list should not be null");
        assertEquals(2, allBookings.size(), "Bookings list size should match");
        assertTrue(allBookings.contains(bookingEntity1), "Bookings list should contain first booking");
        assertTrue(allBookings.contains(bookingEntity2), "Bookings list should contain second booking");
    }

    @org.junit.jupiter.api.Test
    void cancelBooking() {
        // Arrange
        BookingFileDao bookingFileDao = new BookingFileDao();
        List<String> passengerNames = Arrays.asList("John Doe", "Jane Doe");
        BookingEntity bookingEntity = new BookingEntity(1L, 1L, passengerNames);
        bookingFileDao.save(bookingEntity);

        // Act
        bookingFileDao.cancelBooking(1L);
        Optional<BookingEntity> cancelledBookingEntityOptional = bookingFileDao.findById(1L);

        // Assert
        assertFalse(cancelledBookingEntityOptional.isPresent(), "Booking should be cancelled");
    }

    @org.junit.jupiter.api.Test
    void getAllBookingsByPassenger() {
        // Arrange
        BookingFileDao bookingFileDao = new BookingFileDao();
        List<String> passengerNames1 = Arrays.asList("John Doe", "Jane Doe");
        List<String> passengerNames2 = Arrays.asList("Alice", "Bob");
        BookingEntity bookingEntity1 = new BookingEntity(1L, 1L, passengerNames1);
        BookingEntity bookingEntity2 = new BookingEntity(2L, 2L, passengerNames2);
        bookingFileDao.save(bookingEntity1);
        bookingFileDao.save(bookingEntity2);

        // Act
        List<BookingEntity> bookingsByPassenger = bookingFileDao.findByFullName(passengerNames1);

        // Assert
        assertNotNull(bookingsByPassenger, "Bookings list should not be null");
        assertEquals(1, bookingsByPassenger.size(), "Bookings list size should match");
        assertTrue(bookingsByPassenger.contains(bookingEntity1), "Bookings list should contain first booking");
        assertFalse(bookingsByPassenger.contains(bookingEntity2), "Bookings list should not contain second booking");
    }

}
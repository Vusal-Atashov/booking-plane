package dao.impl;

import dao.entity.BookingEntity;
import dao.entity.Cities;
import dao.entity.FlightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

class BookingFileDaoTest {

    private BookingFileDao bookingFileDao;

    @Mock
    private FlightFileDao flightFileDao;

    @Mock
    private FileOutputStream fileOutputStream;

    @Mock
    private ObjectOutputStream objectOutputStream;

    private File bookingFile;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        bookingFile = mock(File.class);

        when(bookingFile.exists()).thenReturn(true);
        when(bookingFile.length()).thenReturn(0L);

        // Mock the file-related operations
        whenNew(FileOutputStream.class).withAnyArguments().thenReturn(fileOutputStream);
        whenNew(ObjectOutputStream.class).withAnyArguments().thenReturn(objectOutputStream);
    }

    @Test
    void saveBooking() throws Exception {
        // Arrange
        FlightEntity flightEntity = new FlightEntity(1L,Cities.DUBAI , Cities.LONDON,LocalDateTime.now(), 100);
        when(flightFileDao.findById(1L)).thenReturn(flightEntity);
        doNothing().when(flightFileDao).cancelFlight(anyLong());
        doNothing().when(flightFileDao).save(any(FlightEntity.class));

        List<String> passengerNames = Arrays.asList("John Doe", "Jane Doe");
        BookingEntity bookingEntity = new BookingEntity(1L, 1L, passengerNames);

        // Act
        bookingFileDao.save(bookingEntity);
        Optional<BookingEntity> savedBookingEntityOptional = bookingFileDao.findById(1L);

        // Assert
        assertTrue(savedBookingEntityOptional.isPresent(), "Booking should be saved");

        // Verify interactions
        verify(flightFileDao).findById(1L);
        verify(flightFileDao).cancelFlight(1L);
        verify(flightFileDao).save(any(FlightEntity.class));
        verify(objectOutputStream).writeObject(any(BookingEntity.class));
    }


    @org.junit.jupiter.api.Test
    void findById() {
    }

    @org.junit.jupiter.api.Test
    void getAllBookings() {

    }

    @org.junit.jupiter.api.Test
    void cancelBooking() {

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
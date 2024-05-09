package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.entity.BookingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

public class BookingFileDAOTest {
    private BookingFileDAO bookingFileDAO;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        bookingFileDAO = new BookingFileDAO(objectMapper);
    }

    @Test
    void saveAndGetAllTest() {
        BookingEntity booking1 = new BookingEntity(1, Arrays.asList("John","Doe"));
        BookingEntity booking2 = new BookingEntity(2, Arrays.asList("Jane"," Smith"));
        assertTrue(bookingFileDAO.save(Arrays.asList(booking1, booking2)));
        Collection<BookingEntity> allBookings = bookingFileDAO.getAll();
        assertEquals(2, allBookings.size());
        assertTrue(allBookings.contains(booking1));
        assertTrue(allBookings.contains(booking2));
    }

    @Test
    void getOneByTest() {
        Predicate<BookingEntity> predicate = booking -> booking.getId() == 1;
        Optional<BookingEntity> bookingOptional = bookingFileDAO.getOneBy(predicate);
        assertTrue(bookingOptional.isPresent());
        assertEquals(1, bookingOptional.get().getId());
    }

    @Test
    void getAllByTest() {
        Predicate<BookingEntity> predicate = booking -> booking.getPassengerNames().contains("John Doe");
        Optional<Collection<BookingEntity>> bookingsOptional = bookingFileDAO.getAllBy(predicate);
        assertTrue(bookingsOptional.isPresent());
        assertTrue(bookingsOptional.get().isEmpty());
    }


    @Test
    void deleteTest() {
        bookingFileDAO.delete(1);
        Optional<BookingEntity> deletedBookingOptional = bookingFileDAO.getOneBy(booking -> booking.getId() == 1);
        assertFalse(deletedBookingOptional.isPresent());
    }
}

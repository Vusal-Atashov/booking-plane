package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BookingDao;
import dao.BookingEntity;
import dao.DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class BookingFileDAO extends BookingDao {
    private static final String RESOURCE_PATH = "src/main/java/resource";
    private static final String BOOKING_FILE_PATH = RESOURCE_PATH.concat("Booking.txt");
    private final ObjectMapper objectMapper;
    public BookingFileDAO(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public BookingDao save(BookingDao bookingDao) {
        try {
            final Path path = Paths.get(BOOKING_FILE_PATH);
            Files.write(path, objectMapper.writeValueAsBytes(bookingDao));
        } catch (IOException e) {
            System.err.println("Error writing students to file");
            e.printStackTrace();
        }
        return bookingDao;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Collection<BookingDao> getAll() {
        return null;
    }

    @Override
    public Optional<BookingDao> getOneBy(Predicate<BookingDao> predicate) {
        return Optional.empty();
    }

    @Override
    public Collection<BookingDao> getAllBY(Predicate<BookingDao> predicate) {
        return null;
    }
}

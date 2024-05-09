
package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BookingDao;
import dao.entity.BookingEntity;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class BookingFileDAO extends BookingDao {
    private static final String RESOURCE_PATH = "src/main/java/resource/";
    private static final String BOOKING_FILE_PATH = RESOURCE_PATH + "Booking.json";
    private final ObjectMapper objectMapper;

    public BookingFileDAO(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean save(Collection<BookingEntity> bookings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKING_FILE_PATH))) {
            bw.write(objectMapper.writeValueAsString(bookings));
            return true;
        } catch (IOException e) {
            System.err.println("Error while saving bookings: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Collection<BookingEntity> getAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE_PATH))) {
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(br, BookingEntity[].class)));
        } catch (IOException e) {
            System.err.println("Error while reading bookings from file: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    @Override
    public Optional<BookingEntity> getOneBy(Predicate<BookingEntity> predicate) {
        return getAll().stream().filter(predicate).findFirst();
    }

    @Override
    public Optional<Collection<BookingEntity>> getAllBy(Predicate<BookingEntity> predicate) {
        return Optional.of(getAll().stream().filter(predicate).toList());
    }

    @Override
    public void delete(int id) {
        Collection<BookingEntity> bookings = getAll();
        bookings.removeIf(booking -> booking.getId() == id);
        save(bookings);
    }
}



package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BookingDao;
import dao.entity.BookingEntity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookingDaoImpl implements BookingDao {

    private static final String RESOURCE_PATH = "src/main/java/resource/";
    private static final String BOOKING_FILE_PATH = RESOURCE_PATH + "bookings.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final List<BookingEntity> bookingEntityList = new ArrayList<>();

    @Override
    public void save(BookingEntity entity) {
        bookingEntityList.add(entity);
    }

    @Override
    public void saveAll(List<BookingEntity> entities) {
        bookingEntityList.addAll(entities);
    }

    @Override
    public Optional<BookingEntity> getById(long id) {
        return bookingEntityList.stream().filter(entity -> entity.getId() == id).findFirst();
    }

    @Override
    public List<BookingEntity> getByFullName(List<String> passengerNames) {
        return bookingEntityList.stream().filter(entity -> entity.getPassengerNames() == passengerNames).toList();
    }

    @Override
    public List<BookingEntity> getAll() {
        return bookingEntityList;
    }

    @Override
    public List<BookingEntity> getAllFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE_PATH))) {
            bookingEntityList.addAll(Arrays.asList(MAPPER.readValue(br, BookingEntity[].class)));
            BookingEntity.MAX_ID = bookingEntityList.size();
            return bookingEntityList;
        } catch (IOException e) {
            System.err.println("Error while reading bookings from file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void saveAllToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKING_FILE_PATH))) {
            bw.write(MAPPER.writeValueAsString(bookingEntityList));
        } catch (IOException e) {
            System.err.println("Error while saving bookings: " + e.getMessage());
        }
    }

    @Override
    public void cancelBooking(int bookingId) {
        List<BookingEntity> bookings = getAll();
        bookings.removeIf(booking -> booking.getId() == bookingId);
    }
}

package dao.impl;

import dao.AppendingObjectOutputStream;
import dao.BookingDao;
import dao.entity.BookingEntity;
import dao.entity.FlightEntity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class BookingFileDao implements BookingDao {
    private final String RESOURCE_PATH = "src/main/java/resource/";
    private final String BOOKING_FILE_PATH = RESOURCE_PATH + "booking.bin";
    private final File file = new File(BOOKING_FILE_PATH);

    public void save(BookingEntity entity) {
        FlightDaoImpl flightDao = new FlightDaoImpl();
        FlightEntity flightEntity = flightDao.findById(entity.getFlightId());
        flightDao.cancelFlight(flightEntity.getId());
        int numOfSeats = flightEntity.getNumOfSeats() - entity.getPassengerNames().size();
        if (numOfSeats < 0) {
            System.out.println(("Not enough seats"));
            flightDao.save(flightEntity);
        } else {
            try (FileOutputStream fos = new FileOutputStream(file, true);
                 ObjectOutputStream oos = file.exists() && file.length() > 0 ?
                         new AppendingObjectOutputStream(fos) : new ObjectOutputStream(fos)) {
                oos.writeObject(entity);
                flightEntity.setNumOfSeats(numOfSeats);
                flightDao.save(flightEntity);
            } catch (IOException e) {
                System.err.println("Error while saving booking to file: " + e.getMessage());
            }
        }
    }

    public List<BookingEntity> findAll() {
        List<BookingEntity> bookingEntityList = new ArrayList<>();
        if (!file.exists()) {
            return bookingEntityList;
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    bookingEntityList.add((BookingEntity) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while reading booking from file: " + e.getMessage());
            return Collections.emptyList();
        }
        return bookingEntityList;
    }

    @Override
    public Optional<BookingEntity> findById(long id) {
        List<BookingEntity> bookingEntityList = findAll();
        return bookingEntityList.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst();
    }

    @Override
    public List<BookingEntity> findByFullName(List<String> passengerNames) {
        List<BookingEntity> bookingEntityList = findAll();
        List<BookingEntity> result = new ArrayList<>();
        for (BookingEntity entity : bookingEntityList) {
            if (new HashSet<>(entity.getPassengerNames()).containsAll(passengerNames)) {
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public void cancelBooking(long bookingId) {
        List<BookingEntity> bookingEntityList = findAll();
        BookingEntity bookingEntity = bookingEntityList.stream()
                .filter(entity -> entity.getId() == bookingId)
                .findFirst()
                .orElse(null);
        if (bookingEntity != null) {
            bookingEntityList.remove(bookingEntity);
            try {
                FileOutputStream fos = new FileOutputStream(file, false);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (BookingEntity entity : bookingEntityList) {
                    oos.writeObject(entity);
                }
                oos.close();
            } catch (IOException e) {
                System.err.println("Error while saving booking to file: " + e.getMessage());
            }
            FlightDaoImpl flightDao = new FlightDaoImpl();
            FlightEntity flightEntity = flightDao.findById(bookingEntity.getFlightId());
            flightDao.cancelFlight(flightEntity.getId());
            flightEntity.setNumOfSeats(flightEntity.getNumOfSeats() + bookingEntity.getPassengerNames().size());
            flightDao.save(flightEntity);
        }
    }
}
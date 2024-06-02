package dao.impl;

import dao.AppendingObjectInputStream;
import dao.AppendingObjectOutputStream;
import dao.FlightDao;
import dao.entity.Cities;
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
import java.util.List;

public class FlightFileDao implements FlightDao {
    private final String RESOURCE_PATH = "src/main/java/resource/";
    private final String FLIGHTS_FILE_PATH = RESOURCE_PATH + "flights.bin";
    private final File file = new File(FLIGHTS_FILE_PATH);

    @Override
    public void save(FlightEntity entity) {
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = new AppendingObjectOutputStream(fos);
            oos.writeObject(entity);
            oos.close();
        } catch (IOException e) {
            System.err.println("Error while saving flight to file: " + e.getMessage());
        }
    }

    @Override
    public List<FlightEntity> findAll() {
        List<FlightEntity> flightEntityList = new ArrayList<>();
        if (!file.exists()) {
            return flightEntityList;
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new AppendingObjectInputStream(fis)) {
            while (true) {
                try {
                    flightEntityList.add((FlightEntity) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while reading flight from file: " + e.getMessage());
            return Collections.emptyList();
        }
        return flightEntityList;
    }


    @Override
    public List<FlightEntity> findByOrigin(String origin) {
        List<FlightEntity> flightEntityList = findAll();
        List<FlightEntity> result = new ArrayList<>();
        for (FlightEntity flightEntity : flightEntityList) {
            if (flightEntity.getOrigin().equals(Cities.getCity(origin))) {
                result.add(flightEntity);
            }
        }
        return result;
    }

    @Override
    public void cancelFlight(long flightId) {

        List<FlightEntity> flightEntityList = findAll();
        FlightEntity flightEntity = flightEntityList.stream()
                .filter(entity -> entity.getId() == flightId)
                .findFirst()
                .orElse(null);
        if (flightEntity != null) {
            flightEntityList.remove(flightEntity);
            try {
                FileOutputStream fos = new FileOutputStream(file, false);
                ObjectOutputStream oos =new AppendingObjectOutputStream(fos);
                for (FlightEntity entity : flightEntityList) {
                    oos.writeObject(entity);
                }
                oos.close();
            } catch (IOException e) {
                System.err.println("Error while saving flight to file: " + e.getMessage());
            }
        }
    }

    @Override
    public FlightEntity findById(long id) {
        List<FlightEntity> flightEntityList = findAll();
        return flightEntityList.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

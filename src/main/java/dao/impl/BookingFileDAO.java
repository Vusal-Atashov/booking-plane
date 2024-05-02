package dao.impl;

import dao.DAO;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class BookingFileDAO implements DAO<String> {
    @Override
    public void save() {

    }

    @Override
    public Collection<String> getAll() {
        return null;
    }

    @Override
    public Optional<String> getOneBy(Predicate<String> predicate) {
        return Optional.empty();
    }

    @Override
    public Collection<String> getAllBY(Predicate<String> predicate) {
        return null;
    }
}

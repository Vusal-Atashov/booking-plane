package dao;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<E>{
    E save(E e);

    Collection<E> getAll();

    Optional<E> getOneBy(Predicate<E> predicate);

    Collection<E> getAllBY(Predicate<E> predicate);

}

package dao;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<E,Q,T> {
    void save();
    Collection<E> getAll();
   Optional<T> getOneBy(Predicate<Q> predicate);
   Collection<E> getAllBY(Predicate<Q> predicate);

}

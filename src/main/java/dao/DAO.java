package dao;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface DAO<E>{
    boolean save(Collection<E> collection);
    Collection<E> getAll();
    Optional<E> getOneBy(Predicate<E> predicate);
    Optional<Collection<E>> getAllBy(Predicate<E> predicate);
    void delete(int id);


}

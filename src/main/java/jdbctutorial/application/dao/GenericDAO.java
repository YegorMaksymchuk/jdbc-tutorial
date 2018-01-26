package jdbctutorial.application.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, ID> {

    T getById(ID id) throws SQLException;

    List<T> getAll();

    void save(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;
}

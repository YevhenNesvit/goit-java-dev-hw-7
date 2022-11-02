package repositories;

import java.sql.SQLException;

public interface Repository<D, L, O> {
    void create(O entity);
    void delete(Integer id);
    void update(O entity);
    D findById(Integer id);
    L findAll() throws SQLException;
}
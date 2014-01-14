package biz.letsweb.sqlite.dao;

/**
 *
 * @author Tomasz
 */
public interface Dao<T> {

    T findByName(String entityName);
    void save(T object) throws Exception;
    void update(T object) throws Exception;
}

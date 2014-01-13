package biz.letsweb.sqlite.dao;

/**
 *
 * @author Tomasz
 */
public interface Dao<T> {

    T findByName(String entityName);
}

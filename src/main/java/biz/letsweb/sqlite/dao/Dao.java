package biz.letsweb.sqlite.dao;

/**
 * 
 * @author Tomasz
 */
public interface Dao<T> {

  T findByName(String entityName);

  int save(T object) throws Exception;

  int update(T object) throws Exception;

  Iterable<T> findAll() throws Exception;

  void delete(T t);
}

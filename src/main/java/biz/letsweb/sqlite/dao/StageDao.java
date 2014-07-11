package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.mvc.model.Stage;
import java.util.List;

/**
 * 
 * @author Tomasz
 */
public interface StageDao extends Dao<Stage> {

  /**
   * 
   * @return @throws Exception
   */
  @Override
  List<Stage> findAll() throws Exception;

  List<Stage> findByProject(String projectName);
}

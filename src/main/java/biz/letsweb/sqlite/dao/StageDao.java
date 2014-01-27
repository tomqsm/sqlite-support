package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;

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
  Stages findAll() throws Exception;

  Stages findByProject(String projectName);
}

package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;

/**
 * 
 * @author Tomasz
 */
public interface ProjectDao extends Dao<Project> {

  void associateToProject(final Project project, final Stage stage);

  void deleteByName(final String name);

  void deleteById(final int id);

}

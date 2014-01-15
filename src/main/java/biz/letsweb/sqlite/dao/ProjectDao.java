package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;

/**
 *
 * @author Tomasz
 */
public interface ProjectDao extends Dao<Project> {

    void saveStageToProject(final String projectName, final Stage stage) throws Exception;
}

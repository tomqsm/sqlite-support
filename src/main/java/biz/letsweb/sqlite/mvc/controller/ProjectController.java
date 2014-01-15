package biz.letsweb.sqlite.mvc.controller;

import biz.letsweb.sqlite.dao.ProjectDao;
import biz.letsweb.sqlite.mvc.model.Project;

/**
 *
 * @author Tomasz
 */
public class ProjectController {
    
    private ProjectDao projectDao;

    public ProjectController(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
    

    Project initializeProject(String projectName) {
        return projectDao.findByName(projectName);
    }
    
}

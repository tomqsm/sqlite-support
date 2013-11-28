package biz.letsweb.sqlite;

import java.util.List;


public interface DataProvidable {

    List<Project> findAllProjects();

    Project getProjectByName(String projectName);

    Project getFocusedProject();

    public boolean addNewProject(Project newProject);

    
}

package biz.letsweb.sqlite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ProjectsProvider implements DataProvidable {

    private Set<Project> projects;

    public ProjectsProvider() {
        projects = new HashSet<>();
        Project project = new Project();
        project.setName("default");
        projects.add(project);
    }

    @Override
    public List<Project> findAllProjects() {
        List<Project> localProjects = new ArrayList<>();
        localProjects.addAll(projects);
        return localProjects;
    }

    @Override
    public Project getProjectByName(String projectName) {
        Project foundProject = null;
        for(Project p : projects){
            if(p.getName().equals(projectName)){
                foundProject = p;
            }
        }
        return foundProject;
    }

    @Override
    public Project getFocusedProject() {
        Project foundProject = null;
        for(Project p : projects){
            if(p.isFocused() == true){
                foundProject = p;
            }
        }
        return foundProject;
    }

    @Override
    public boolean addNewProject(Project newProject) {
        return projects.add(newProject);
    }
}

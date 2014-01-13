package biz.letsweb.sqlite;

import biz.letsweb.sqlite.mvc.model.Project;

public interface ApplicationInterface {

    /**
     * Shows all projects you undertook.
     */
    void showAllProjects();

    /**
     * Shows name of project you are working on.
     */
    void showCurrentProject();

    /**
     * Shows when you started given project.
     */
    void showProjectStartTime(Project project);

    /**
     * Shows when you started working on project you are working on.
     */
    void showProjectStartTime();

    /**
     * Shows duration of project e.g. 123 days 13 hours 23 minutes.
     */
    void showProjectDuration();

    /**
     * Shows when the project was last accessed.
     */
    void showProjectEndTime();

    /**
     *
     * @param project
     */
    void showTasksInProject(Project project);
}

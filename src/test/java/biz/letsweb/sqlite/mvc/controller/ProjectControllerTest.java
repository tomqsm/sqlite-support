package biz.letsweb.sqlite.mvc.controller;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.dao.ProjectDaoImpl;
import biz.letsweb.sqlite.mvc.model.Project;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomasz
 */
public class ProjectControllerTest {

    private ProjectController pc;

    @Before
    public void setUp() {
        pc = new ProjectController(new ProjectDaoImpl());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canInitialiseProjectFromDataBase() throws Exception {
        TimingSqlite timingDb = TimingSqlite.getTimingDbSingleton();
        timingDb.create();
        Project project = pc.initializeProject("tomtom");
        Assert.assertEquals("tomtom", project.getName());
        System.out.println(project.getStages().size());
    }

    @Test
    public void youCanAddStagesToProject() {

    }

}

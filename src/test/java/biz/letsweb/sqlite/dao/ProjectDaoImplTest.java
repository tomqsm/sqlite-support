package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.mvc.model.Project;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class ProjectDaoImplTest {

    private ProjectDao projectDao;

    @Before
    public void setUp() {
        projectDao = new ProjectDaoImpl();
        TimingSqlite.getTimingDbSingleton().create();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByName() {
        final Project project = projectDao.findByName("tomtom");
        Assert.assertNotNull(project);
    }

    @Test
    public void testSave() throws Exception {
        Project project = new Project();
        project.setName("letsweb");
        projectDao.save(project);
        final Project p = projectDao.findByName("letsweb");
        Assert.assertNotNull(p);
    }
}

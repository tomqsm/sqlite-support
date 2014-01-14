package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.mvc.model.Project;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class ProjectDaoImplTest {

    private ProjectDao projectDao;

    @BeforeClass
    public static void setUpClass() {
        TimingSqlite.getTimingDbSingleton().create();
    }

    @Before
    public void setUp() {
        projectDao = new ProjectDaoImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByName() {
        final Project project = projectDao.findByName("tomtom");
        assertNotNull(project);
    }

    @Test
    public void testSave() throws Exception {
        Project project = new Project();
        project.setName("letsweb");
        projectDao.save(project);
        final Project p = projectDao.findByName("letsweb");
        Assert.assertNotNull(p);
    }

    @Test
    public void testUpdate() throws Exception {
        Project project = projectDao.findByName("tomtom");
        assertNotNull(project);
        Assert.assertEquals(1, project.getId());
        project.setName("tt");
        projectDao.update(project);
    }
}

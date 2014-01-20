package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class StageDaoImplTest {

    private StageDao stageDao;

    @BeforeClass
    public static void setUpClass() {
        SqliteUtils.drop("activities_types", "activities", "types");
        SqliteUtils.create();
    }

    @Before
    public void setUp() {
        stageDao = new StageDaoImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByName() {
        String expectedName = "documentation";
        final Stage stage = stageDao.findByName(expectedName);
        assertNotNull(stage);
        assertEquals(expectedName, stage.getName());
    }

    @Test
    public void testSave() throws Exception {
        Stage stage = new Stage();
        stage.setName("test stage");
        stageDao.save(stage);
        stage = stageDao.findByName("test stage");
        assertEquals("test stage", stage.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        Stage s = stageDao.findByName("documentation");
        s.setName("robienie dziórek");
        stageDao.update(s);
        s = stageDao.findByName("robienie dziórek");
        assertEquals("robienie dziórek", s.getName());
    }

    @Test
    public void findsAllStages() throws Exception {
        Stage stage = new Stage();
        stage.setName("test stage");
        stageDao.save(stage);
        assertEquals(5, stageDao.findAll().size());
    }

//    @Test
    public void findStagesByProject() {
        ProjectDao projectDao = new ProjectDaoImpl();
        final Project project = projectDao.findByName("tomtom");
        final Stages stages = stageDao.findByProject(project);
        assertNotNull(stages);
    }

}

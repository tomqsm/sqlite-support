package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.mvc.model.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kusmierc
 */
public class StageDaoImplTest {

    private StageDao stageDao;

    @BeforeClass
    public static void setUpClass() {
        TimingSqlite.getTimingDbSingleton().create();
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

}

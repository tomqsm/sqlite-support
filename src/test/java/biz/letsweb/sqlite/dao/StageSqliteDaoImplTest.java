package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.DbConstructor;
import biz.letsweb.sqlite.SqliteDataSourceProvider;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Stage;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author kusmierc
 */
public class StageSqliteDaoImplTest {

    private ProjectDao projectDao;
    private StageDao stageDao;
    private ApplicationContext ctx;

    public StageSqliteDaoImplTest() throws ConfigurationException {
        ctx = new FileSystemXmlApplicationContext("file:src/main/resources/spring.xml");
        ctx.getBean(DbConstructor.class).drop(Configuration.TABLE_NAMES.getValues().toArray(new String[]{}));
        ctx.getBean(DbConstructor.class).create();
    }

    @Before
    public void setUp() {
        projectDao = ctx.getBean(ProjectSqliteDaoImpl.class);
        stageDao = ctx.getBean(StageSqliteDaoImpl.class);
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
        stageDao.delete(stage);
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

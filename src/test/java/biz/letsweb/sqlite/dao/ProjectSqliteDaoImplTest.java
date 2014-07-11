package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.DbConstructor;
import biz.letsweb.sqlite.SqliteDataSourceProvider;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;
import org.apache.commons.configuration.ConfigurationException;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class ProjectSqliteDaoImplTest {

    private ProjectDao projectDao;
    private StageDao stageDao;
    private ApplicationContext ctx;

    public ProjectSqliteDaoImplTest() throws ConfigurationException {
        ctx = new FileSystemXmlApplicationContext("file:src/main/resources/spring.xml");
        ctx.getBean(DbConstructor.class).drop(Configuration.TABLE_NAMES.getValues().toArray(new String[]{}));
        ctx.getBean(DbConstructor.class).create();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
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
        final Project project = projectDao.findByName("tomtom");
        assertNotNull(project);
    }

    @Test
    public void testUpdate() throws Exception {
        Project project = projectDao.findByName("tomtom");
        assertNotNull(project);
        Assert.assertEquals(1, project.getId());
        project.setName("tt");
        projectDao.update(project);
    }

    @Test
    public void findsAllProjects() throws Exception {
        final Iterable<Project> projects = projectDao.findAll();
        Assertions.assertThat(projects).hasSize(2);
        Project project = new Project();
        project.setName("new project");
        projectDao.save(project);
        Assertions.assertThat(projectDao.findAll()).hasSize(3);
    }

    @Test
    public void associateStage() throws Exception {
        Project p = new Project();
        p.setName("kumazin");
        int pId = projectDao.save(p);
        Stage s = new Stage();
        s.setName("kumzin stage");
        int sId = stageDao.save(s);
        projectDao.associateToProject(p, s);
        projectDao.deleteByName("kumazin");
    }
    @Test
    public void createNewProject() throws Exception{
        Project p = new Project();
        p.setName("project1");
        p.setType("project");
        projectDao.save(p);
        Project pp = projectDao.findByName("project1");
        Assertions.assertThat(pp.equals(p));
    }
}

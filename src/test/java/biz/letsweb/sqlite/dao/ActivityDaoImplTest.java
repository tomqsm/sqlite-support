package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.DbConstructor;
import biz.letsweb.sqlite.SqliteDataSourceProvider;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Activity;
import biz.letsweb.sqlite.mvc.model.Project;
import org.apache.commons.configuration.ConfigurationException;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author toks
 */
public class ActivityDaoImplTest {

    private ActivityDao activityDao;
    private ApplicationContext ctx;

    public ActivityDaoImplTest() throws ConfigurationException {
        ctx = new FileSystemXmlApplicationContext("file:src/main/resources/spring.xml");
        ctx.getBean(DbConstructor.class).drop(Configuration.TABLE_NAMES.getValues().toArray(new String[]{}));
        ctx.getBean(DbConstructor.class).create();
    }

    @BeforeClass
    public static void setUpClass() throws ConfigurationException {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        activityDao = ctx.getBean(ActivitySqliteDaoImpl.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindById() {
        final Activity activity = activityDao.findById(5);
        assertThat(activity).isNotNull();
        System.out.println(activity);
    }

    @Test
    public void findRecentSubActivity() {
        final Activity activity = activityDao.findRecentSubActivity();
        assertThat(activity).isNotNull();
        assertThat(activity.getId()).isEqualTo(12);
        // TODO test further
    }

    @Test
    public void findRecentActivity() {
        final Activity activity = activityDao.findRecentActivity();
        assertThat(activity).isNotNull();
        assertThat(activity.getId()).isEqualTo(7);

        // TODO test further
    }

    //@Test
    public void testAddingNewActivityByType() throws Exception {
        // available types: project, story, task, stage, history
        Activity project = new Project();
        project.setName("testProject");
        project.setType("project");
        // what if type 'project' doesn't exist ? should it be created
        // if type 'project' doesn't exist user should be prompted and must confirm adding is desired
        activityDao.save(project);

    }

    @Test
    public void testCreateNewProject() {

    }

}

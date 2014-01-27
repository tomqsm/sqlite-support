package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Projects;
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

/**
 * 
 * @author kusmierc
 */
public class ProjectDaoImplTest {

  private ProjectDao projectDao;

  @BeforeClass
  public static void setUpClass() throws ConfigurationException {
    SqliteUtils.drop(Configuration.TABLE_NAMES.getValues().toArray(new String[] {}));
    SqliteUtils.create();
  }

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {
    projectDao = new ProjectDaoImpl();
  }

  @After
  public void tearDown() {}

  @Test
  public void testFindByName() {
    final Project project = projectDao.findByName("tomtom");
    assertNotNull(project);
  }

  @Test
  public void savesAndDeletesProject() throws Exception {
    Project project = new Project();
    project.setName("letsweb1");
    projectDao.save(project);
    final Project p = projectDao.findByName("letsweb1");
    Assert.assertNotNull(p);
    final Projects findAll = (Projects) projectDao.findAll();
    int size1 = findAll.size();
    projectDao.delete(p);
    Assertions.assertThat(projectDao.findAll()).hasSize(size1 - 1);
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

  // @Test
  public void canCaveStageToProject() throws Exception {
    // TODO not working cos you need to save type in types table
    // before refering it to a stage in activities table
    Project p = new Project();
    p.setName("kumazin");
    projectDao.save(p);
    Stage s = new Stage();
    s.setName("kumzin stage");
    projectDao.saveStageToProject("kumazin", s);
  }
}

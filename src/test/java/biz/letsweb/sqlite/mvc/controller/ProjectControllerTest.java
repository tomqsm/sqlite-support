package biz.letsweb.sqlite.mvc.controller;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.dao.ProjectDaoImpl;
import biz.letsweb.sqlite.mvc.model.Project;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Tomasz
 */
public class ProjectControllerTest {

  private ProjectController pc;

  @BeforeClass
  public static void setUpClass() throws ConfigurationException {
    SqliteUtils.drop(Configuration.TABLE_NAMES.getValues().toArray(new String[] {}));
    // SqliteUtils.getTimingDbSingleton().create();
  }

  @Before
  public void setUp() {
    pc = new ProjectController(new ProjectDaoImpl());
  }

  @After
  public void tearDown() {}

  @Test
  public void canInitialiseProjectFromDataBase() throws Exception {
    SqliteUtils.create();
    Project project = pc.initializeProject("tomtom");
    Assert.assertEquals("tomtom", project.getName());
    System.out.println(project.getStages().size());
  }

  @Test
  public void youCanAddStagesToProject() {

  }

}

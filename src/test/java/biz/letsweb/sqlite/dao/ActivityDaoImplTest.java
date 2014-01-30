package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Activity;
import org.apache.commons.configuration.ConfigurationException;
import static org.fest.assertions.Assertions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author toks
 */
public class ActivityDaoImplTest {

  private ActivityDao activityDao;

  public ActivityDaoImplTest() {
    activityDao = new ActivityDaoImpl();
  }

  @BeforeClass
  public static void setUpClass() throws ConfigurationException {
    SqliteUtils.drop(Configuration.TABLE_NAMES.getValues().toArray(new String[] {}));
    SqliteUtils.create();
  }

  @AfterClass
  public static void tearDownClass() {}


  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test
  public void testFindById() {
    final Activity activity = activityDao.findById(11);
    assertThat(activity).isNotNull();
  }

}

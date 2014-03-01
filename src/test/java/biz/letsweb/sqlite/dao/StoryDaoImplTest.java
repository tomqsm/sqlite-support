package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Story;
import org.apache.commons.configuration.ConfigurationException;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author toks
 */
public class StoryDaoImplTest {

  public StoryDaoImplTest() {}

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
  public void testFindByName() {
    StorySqliteDaoImpl storyDao = new StorySqliteDaoImpl();
    Story result = storyDao.findByName("story setting-up");
    assertThat(result.getName()).isEqualTo("story setting-up");
  }
}

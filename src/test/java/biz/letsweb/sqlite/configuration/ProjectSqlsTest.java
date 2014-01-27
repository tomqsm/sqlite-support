package biz.letsweb.sqlite.configuration;

import biz.letsweb.sqlite.SqliteUtils;
import org.fest.assertions.Assertions;
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
public class ProjectSqlsTest {

  public ProjectSqlsTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of values method, of class ProjectSqls.
   */
  @Test
  public void testValues() {
    ProjectSqls[] result = ProjectSqls.values();
    assertThat(result).hasSize(4);
    final ProjectSqls valueOf = ProjectSqls.valueOf("FIND_ALL");
    assertThat(valueOf).isEqualTo(ProjectSqls.FIND_ALL);
  }

}

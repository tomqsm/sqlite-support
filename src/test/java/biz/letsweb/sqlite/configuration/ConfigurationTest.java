package biz.letsweb.sqlite.configuration;

import biz.letsweb.sqlite.SqliteUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Tomasz
 */
public class ConfigurationTest {

  public ConfigurationTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void savesPropertyWhenChanged() throws ConfigurationException {
    long currentTime = System.nanoTime();
    Configuration.TIME.setValue(String.valueOf(currentTime));
    assertEquals(String.valueOf(currentTime), Configuration.TIME.toString());
  }
}

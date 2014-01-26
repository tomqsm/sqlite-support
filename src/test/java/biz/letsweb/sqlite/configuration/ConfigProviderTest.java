package biz.letsweb.sqlite.configuration;

import java.util.List;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Tomasz
 */
public class ConfigProviderTest {

  public ConfigProviderTest() {}

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
  public void testInitialiseXMLConfiguration() {
    final int first = 0;
    ConfigProvider configProvider = new ConfigProvider();
    assertNotNull(configProvider);
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    final List<HierarchicalConfiguration> props =
        configProvider.getXMLConfiguration().configurationsAt("properties");
    HierarchicalConfiguration hc = props.get(first);
    final String color = hc.getString("color");
    assertEquals("blue", color);
  }

  @Test
  public void throwsWhenFileNotFound() {
    final int first = 0;
    final String expectedMessage = "Cannot locate configuration source configurationWrong.xml";
    thrown.expectMessage(expectedMessage);
    ConfigProvider configProvider = new ConfigProvider();
    assertNotNull(configProvider);
    configProvider.initialiseXMLConfiguration("configurationWrong.xml");
    final List<HierarchicalConfiguration> props =
        configProvider.getXMLConfiguration().configurationsAt("properties");
    HierarchicalConfiguration hc = props.get(first);
    final String color = hc.getString("color");
    assertEquals("blue", color);
  }

  @Test
  public void retrievesSqlFileNameFromConfiguration() {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    final String fileName =
        configProvider.getXMLConfiguration().getString("sql/fromFile/createTablesSql");
    assertEquals("sql/timingdb/dev_create.sql", fileName);
  }

  @Test
  public void retrievesTableNames() {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    final List<Object> list = configProvider.getXMLConfiguration().getList("sql/tables/table");
    assertNotNull(list);
    Assertions.assertThat(list).hasSize(4);
  }

  @Test
  public void findsSqlStringForFindProjectByName() {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    final String sql = configProvider.getXMLConfiguration().getString("sql/project/findByName");
    assertNotNull(sql);
    System.out.println(sql);
  }

  @Test
  public void readsSqlContentFromFileViaConfiguration() {
    Configuration.CREATE_TABLES_SQL.toString();
  }

}

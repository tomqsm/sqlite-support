package biz.letsweb.sqlite.dao;

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
public class TypesTest {

  public TypesTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of values method, of class Types.
   */
  @Test
  public void lookupTest() {
    assertThat(Types.LOOKUP.get(1)).isEqualTo(Types.PROJECT);
    assertThat(Types.LOOKUP.get(4)).isEqualTo(Types.TASK);
  }
}

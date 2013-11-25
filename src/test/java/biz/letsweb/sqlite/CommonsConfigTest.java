/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.letsweb.sqlite;

import biz.letsweb.sqlite.CommonsConfig;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomasz
 */
public class CommonsConfigTest {

    public CommonsConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getProperty method, of class CommonsConfig.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        System.out.println(CommonsConfig.APP_PROPS.getString("developer"));
    }

    @Test
    public void testSaveProperty() throws ConfigurationException {
        System.out.println("saveProperty");
        CommonsConfig.APP_PROPS.setProperty("setProp", "test");
        CommonsConfig.APP_PROPS.save();
    }
}
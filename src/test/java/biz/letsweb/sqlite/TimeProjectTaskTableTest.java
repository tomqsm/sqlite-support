/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.letsweb.sqlite;

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
public class TimeProjectTaskTableTest {

    public TimeProjectTaskTableTest() {
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

    @Test
    public void testCreateTable() throws Exception {
        System.out.println("createTable");
        TimeProjectTaskTable instance = new TimeProjectTaskTable();
        instance.createTable();
        instance.dropTable();
    }
    
}
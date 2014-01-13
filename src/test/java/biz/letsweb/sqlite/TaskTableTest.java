package biz.letsweb.sqlite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TaskTableTest {

    public TaskTableTest() {
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
        TaskTable instance = new TaskTable();
        instance.createTable();
        instance.dropTable();
    }
    
}
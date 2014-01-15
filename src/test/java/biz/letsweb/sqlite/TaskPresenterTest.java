package biz.letsweb.sqlite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

public class TaskPresenterTest {

    private static final Logger LOG = LoggerFactory.getLogger(TaskPresenterTest.class);
    private Task task;
    private TaskPresenter taskPresenter;

    @Before
    public void setUp() {
        taskPresenter = new TaskPresenter();
        task = new Task();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDisplayDuration() throws ApplicationException {
        //about 600 H per task seems adequate that's the maximum 
        long now = System.currentTimeMillis();
        task.setStartTime(now - 2139569015);
        task.setStopTime(now);
        final String duration = taskPresenter.displayDuration(task);
        LOG.info("{}", duration);
        assertEquals("594 godz. 19 min. 29 sek. ", duration);
    }
}
package biz.letsweb.sqlite.mvc.model;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomasz
 */
public class ActivityTest {
    private Project p;
    
    @Before
    public void setUp() {
        p = new Project();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void projectHasGotStages() {
        Stages stages = p.getStages();
        Assert.assertNotNull(stages);
    }
    
    @Test
    public void stagesAreNoneBeforeTheirExplicitInitialisation(){
        Stages ss = p.getStages();
        assertThat(ss).isEmpty();
    }
    
    @Test
    public void projectHasActivities(){
        Activities aa = p.getActivities();
    }
    
}

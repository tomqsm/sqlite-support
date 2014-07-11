package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.DbConstructor;
import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.mvc.model.Story;
import org.apache.commons.configuration.ConfigurationException;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author toks
 */
public class StoryDaoImplTest {

    private StoryDao storyDao;
    private ApplicationContext ctx;

    public StoryDaoImplTest() throws ConfigurationException {
        ctx = new FileSystemXmlApplicationContext("file:src/main/resources/spring.xml");
        ctx.getBean(DbConstructor.class).drop(Configuration.TABLE_NAMES.getValues().toArray(new String[]{}));
        ctx.getBean(DbConstructor.class).create();
    }
    @Before
    public void setUp() {
        storyDao = ctx.getBean(StorySqliteDaoImpl.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByName() {
        Story result = storyDao.findByName("story setting-up");
        assertThat(result.getName()).isEqualTo("story setting-up");
    }
}

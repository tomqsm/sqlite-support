package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.ProjectSqls;
import biz.letsweb.sqlite.mvc.model.Activities;
import biz.letsweb.sqlite.mvc.model.Activity;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Projects;
import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Tomasz
 */
public final class ProjectDaoImpl implements ProjectDao {

  private final JdbcTemplate jdbcTemplate;
  private static final Logger LOG = LoggerFactory.getLogger(ProjectDaoImpl.class);

  public ProjectDaoImpl() {
    jdbcTemplate = new JdbcTemplate(SqliteUtils.getDataSource());
  }

  @Override
  public Project findByName(final String projectName) {
    return jdbcTemplate.queryForObject(ProjectSqls.FIND_BY_NAME.getSql(),
        new Object[] {projectName}, new RowMapper<Project>() {
          @Override
          public Project mapRow(ResultSet rs, int i) throws SQLException {
            Project p = new Project();
            p.setId(rs.getInt(1));
            p.setName(rs.getString(2));
            return p;
          }
        });
  }

  @Override
  public int save(final Project project) throws SQLException {
    return jdbcTemplate.update(ProjectSqls.SAVE.getSql(), new Object[] {project.getName()});
  }

  @Override
  public int update(final Project project) throws Exception {
    return jdbcTemplate.update(ProjectSqls.UPDATE.getSql(), new Object[] {project.getName(),
        project.getId()});
  }

  @Override
  public Projects findAll() throws Exception {
    final Projects pp = new Projects();
    jdbcTemplate.query(ProjectSqls.FIND_ALL.getSql(), new RowMapper<Project>() {

      @Override
      public Project mapRow(ResultSet rs, int i) throws SQLException {
        Project p = new Project();
        p.setId(rs.getInt(1));
        p.setName(rs.getString(2));
        pp.add(p);
        return p;
      }
    });
    return pp;
  }

  @Override
  public void delete(final Project t) {
    deleteById(t.getId());
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update(ProjectSqls.DELETE.getSql(), new Object[] {id});
  }

  @Override
  public void deleteByName(String name) {
    final Project p = findByName(name);
    final StageDao sd = new StageDaoImpl();
    final Stages ss = sd.findByProject(name);
    for (Stage s : ss) {
      sd.delete(s);
    }
    delete(p);
  }

  @Override
  public final void associateToProject(final Project project, final Stage stage) {
    final Project p = findByName(project.getName());
    final StageDao stageDao = new StageDaoImpl();
    final Stage s = stageDao.findByName(stage.getName());
    jdbcTemplate.update(ProjectSqls.ASSOCIATE_STAGE.getSql(), new Object[] {p.getId(), s.getId()});
  }

  @Override
  public final Activities<Activity> findTreeByTaskId(int id) {
    final Activities<Activity> aa = new Activities<>();
    Activity activity = new ActivityDaoImpl().findById(id);
    aa.add(activity);
    Activity subActivity = null;
    try {
    while(true){
        subActivity = subQueryForTree(activity.getId());
        aa.add(subActivity);
        activity = subActivity;
    }
    } catch(EmptyResultDataAccessException e){
        LOG.trace("Emptied result set expected exception ignored.");
    }
    return aa;
  }

  private Activity subQueryForTree(int id) {
    return jdbcTemplate.queryForObject(ProjectSqls.FIND_TREE_BY_TASK_ID.getSql(),
        new Object[] {id}, new RowMapper<Activity>() {

          @Override
          public Activity mapRow(ResultSet rs, int i) throws SQLException {
            Activity a = new Activity();
            a.setId(rs.getInt(1));
            a.setName(rs.getString(2));
            return a;
          }
        });
  }

  @Override
  public Project findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.ProjectSqls;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Projects;
import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Tomasz
 */
public final class ProjectSqliteDaoImpl implements ProjectDao {

  private final JdbcTemplate jdbcTemplate;
  private static final Logger LOG = LoggerFactory.getLogger(ProjectSqliteDaoImpl.class);

  public ProjectSqliteDaoImpl() {
    final DataSource dataSource = SqliteUtils.getDataSource();
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public Project findByName(final String projectName) {
    final RowMapper<Project> rowMapper = new RowMapper<Project>() {
      @Override
      public Project mapRow(ResultSet rs, int i) throws SQLException {
        Project p = new Project();
        p.setId(rs.getInt(1));
        p.setName(rs.getString(2));
        return p;
      }
    };
    final Object[] params = new Object[] {projectName};
    final String sql = ProjectSqls.FIND_BY_NAME.getSql();
    return jdbcTemplate.queryForObject(sql, params, rowMapper);
  }

  @Override
  public int save(final Project project) throws SQLException {
    final String sql = ProjectSqls.SAVE.getSql();
    final Object[] params = new Object[] {project.getName()};
    return jdbcTemplate.update(sql, params);
  }

  @Override
  public int update(final Project project) throws Exception {
    final String sql = ProjectSqls.UPDATE.getSql();
    final Object[] params = new Object[] {project.getName(), project.getId()};
    return jdbcTemplate.update(sql, params);
  }

  @Override
  public Projects findAll() throws Exception {
    final Projects pp = new Projects();
    final String sql = ProjectSqls.FIND_ALL.getSql();
    final RowMapper<Project> rowMapper = new RowMapper<Project>() {

      @Override
      public Project mapRow(ResultSet rs, int i) throws SQLException {
        Project p = new Project();
        p.setId(rs.getInt(1));
        p.setName(rs.getString(2));
        pp.add(p);
        return p;
      }
    };

    jdbcTemplate.query(sql, rowMapper);
    return pp;
  }

  @Override
  public void delete(final Project t) {
    deleteById(t.getId());
  }

  @Override
  public void deleteById(int id) {
    final String sql = ProjectSqls.DELETE.getSql();
    final Object[] params = new Object[] {id};
    jdbcTemplate.update(sql, params);
  }

  @Override
  public void deleteByName(String name) {
    final Project p = findByName(name);
    final StageDao sd = new StageSqliteDaoImpl();
    final Stages ss = sd.findByProject(name);
    for (Stage s : ss) {
      sd.delete(s);
    }
    delete(p);
  }

  @Override
  public final void associateToProject(final Project project, final Stage stage) {
    final Project p = findByName(project.getName());
    final StageDao stageDao = new StageSqliteDaoImpl();
    final Stage s = stageDao.findByName(stage.getName());
    final String sql = ProjectSqls.ASSOCIATE_STAGE.getSql();
    final Object[] params = new Object[] {p.getId(), s.getId()};
    jdbcTemplate.update(sql, params);
  }



//  private Activity subQueryForTree(int id) {
//    final RowMapper<Activity> rowMapper = new RowMapper<Activity>() {
//
//      @Override
//      public Activity mapRow(ResultSet rs, int i) throws SQLException {
//        Activity a = new Activity();
//        a.setId(rs.getInt(1));
//        a.setName(rs.getString(2));
//        return a;
//      }
//    };
//    final String sql = ProjectSqls.FIND_TREE_BY_TASK_ID.getSql();
//    final Object[] params = new Object[] {id}; //WHERE aas.sub_activity_id=?
//    return jdbcTemplate.queryForObject(sql, params, rowMapper); 
//  }

  @Override
  public Project findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

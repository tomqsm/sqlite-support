package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.ProjectSqls;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Projects;
import biz.letsweb.sqlite.mvc.model.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Tomasz
 */
public final class ProjectDaoImpl implements ProjectDao {
  private JdbcTemplate jdbcTemplate;

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
  public void save(final Project project) throws SQLException {
    jdbcTemplate.update(ProjectSqls.SAVE.getSql(), new Object[] {project.getName()});
  }

  @Override
  public void update(final Project project) throws Exception {
    jdbcTemplate.update(ProjectSqls.UPDATE.getSql(),
        new Object[] {project.getName(), project.getId()});
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
    jdbcTemplate.update(ProjectSqls.DELETE.getSql(), new Object[] {t.getId()});
  }

  @Override
  public void associateToProject(final Project project, final Stage stage) {
    final Project p = findByName(project.getName());
    final StageDao stageDao = new StageDaoImpl();
    final Stage s = stageDao.findByName(stage.getName());
    jdbcTemplate.update(ProjectSqls.ASSOCIATE_STAGE.getSql(), new Object[] {p.getId(), s.getId()});
  }
}

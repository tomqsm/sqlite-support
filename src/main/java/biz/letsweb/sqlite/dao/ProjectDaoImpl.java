package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.ProjectSqls;
import biz.letsweb.sqlite.configuration.StageSqls;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Projects;
import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
  public Project findByName(String projectName) {
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
  public void save(Project project) throws SQLException {
    jdbcTemplate.update(ProjectSqls.SAVE.getSql(), new Object[] {project.getName()});
  }

  @Override
  public void update(Project project) throws Exception {
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
  public void saveStageToProject(final String projectName, final Stage stage) throws SQLException,
      Exception {
    StageDao stageDao = new StageDaoImpl();
    stageDao.save(stage);
    Stage savedStage = stageDao.findByName(stage.getName());
    // TODO not working cos you need to save type in types table
    // before refering it to a stage in activities table
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(ProjectSqls.SAVE.getSql());
    ps.setString(1, projectName);
    ps.setInt(2, savedStage.getId());
    ps.executeUpdate();
    ps.close();
    con.close();
  }

  @Override
  public void delete(Project t) {
    jdbcTemplate.update(ProjectSqls.DELETE.getSql(), new Object[] {t.getId()});
  }
}

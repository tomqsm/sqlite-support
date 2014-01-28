package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.StageSqls;
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
public final class StageDaoImpl implements StageDao {
  private JdbcTemplate jdbcTemplate;

  public StageDaoImpl() {
    jdbcTemplate = new JdbcTemplate(SqliteUtils.getDataSource());
  }

  @Override
  public Stage findByName(String stageName) {
    return jdbcTemplate.queryForObject(StageSqls.FIND_BY_NAME.getSql(), new Object[] {stageName},
        new RowMapper<Stage>() {
          @Override
          public Stage mapRow(ResultSet rs, int i) throws SQLException {
            Stage s = new Stage();
            s.setId(rs.getInt(1));
            s.setName(rs.getString(2));
            return s;
          }
        });
  }

  @Override
  public int save(Stage stage) throws SQLException {
    return jdbcTemplate.update(StageSqls.SAVE.getSql(), new Object[] {stage.getName()});
  }

  @Override
  public int update(Stage stage) throws Exception {
    return jdbcTemplate.update(StageSqls.UPDATE.getSql(),
        new Object[] {stage.getName(), stage.getId()});
  }

  @Override
  public Stages findAll() throws Exception {
    final Stages ss = new Stages();
    jdbcTemplate.query(StageSqls.FIND_ALL.getSql(), new RowMapper<Stage>() {

      @Override
      public Stage mapRow(ResultSet rs, int i) throws SQLException {
        Stage s = new Stage();
        s.setId(rs.getInt(1));
        s.setName(rs.getString(2));
        ss.add(s);
        return s;
      }
    });
    return ss;
  }

  @Override
  public Stages findByProject(String projectName) {
    final Stages ss = new Stages();
    jdbcTemplate.query(StageSqls.FIND_BY_PROJECT_NAME.getSql(), new Object[] {projectName},
        new RowMapper<Stage>() {

          @Override
          public Stage mapRow(ResultSet rs, int i) throws SQLException {
            Stage s = new Stage();
            s.setId(rs.getInt(1));
            s.setName(rs.getString(2));
            ss.add(s);
            return s;
          }
        });
    return ss;
  }

  @Override
  public void delete(Stage stage) {
    jdbcTemplate.update(StageSqls.DELETE.getSql(), new Object[] {stage.getId()});
  }
}

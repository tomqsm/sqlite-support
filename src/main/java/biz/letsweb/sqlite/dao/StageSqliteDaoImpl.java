package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.configuration.StageSqls;
import biz.letsweb.sqlite.mvc.model.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Tomasz
 */
public final class StageSqliteDaoImpl implements StageDao {

    private JdbcTemplate jdbcTemplate;

    @Override
//    public Stage findByName(String stageName) {
//        return jdbcTemplate.queryForObject(StageSqls.FIND_BY_NAME.getSql(), new Object[]{stageName},
//                new RowMapper<Stage>() {
//                    @Override
//                    public Stage mapRow(ResultSet rs, int i) throws SQLException {
//                        Stage s = new Stage();
//                        s.setId(rs.getInt(1));
//                        s.setName(rs.getString(2));
//                        return s;
//                    }
//                });
//    }
  public Stage findByName(String stageName) {
    final RowMapper<Stage> rowMapper = new RowMapper<Stage>() {

      @Override
      public Stage mapRow(ResultSet rs, int i) throws SQLException {
        Stage s = new Stage();
        s.setId(rs.getInt(1));
        s.setName(rs.getString(2));
        return s;
      }
    };
    final String sql = StageSqls.FIND_BY_NAME.getSql();
    final Object[] params = new Object[] {stageName}; //WHERE aas.sub_activity_id=?
    return jdbcTemplate.queryForObject(sql, params, rowMapper); 
  }
    
    @Override
    public int save(Stage stage) throws SQLException {
        return jdbcTemplate.update(StageSqls.SAVE.getSql(), new Object[]{stage.getName()});
    }

    @Override
    public int update(Stage stage) throws Exception {
        return jdbcTemplate.update(StageSqls.UPDATE.getSql(),
                new Object[]{stage.getName(), stage.getId()});
    }

    @Override
    public List<Stage> findAll() throws Exception {
        final List<Stage> ss = new ArrayList<>();
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
    public List<Stage> findByProject(String projectName) {
        final List<Stage> ss = new ArrayList<>();
        jdbcTemplate.query(StageSqls.FIND_BY_PROJECT_NAME.getSql(), new Object[]{projectName},
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
        jdbcTemplate.update(StageSqls.DELETE.getSql(), new Object[]{stage.getId()});
    }

    @Override
    public Stage findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}

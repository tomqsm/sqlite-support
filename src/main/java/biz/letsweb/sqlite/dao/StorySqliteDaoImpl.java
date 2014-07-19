package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.configuration.StorySqls;
import biz.letsweb.sqlite.mvc.model.Story;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StorySqliteDaoImpl implements StoryDao {

  private JdbcTemplate jdbcTemplate;

  @Override
  public Story findByName(String storyName) {
    final RowMapper<Story> rowMapper = new RowMapper<Story>() {

      @Override
      public Story mapRow(ResultSet rs, int i) throws SQLException {
        Story s = new Story();
        s.setId(rs.getInt(1));
        s.setName(rs.getString(2));
        return s;
      }
    };
    final String sql = StorySqls.FIND_BY_NAME.getSql();
    final Object[] params = new Object[] {storyName}; // WHERE aas.sub_activity_id=?
    return jdbcTemplate.queryForObject(sql, params, rowMapper);
  }

  @Override
  public int save(Story object) throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int update(Story object) throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Iterable<Story> findAll() throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Story t) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Story findById(int id) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
    // methods, choose Tools |
    // Templates.
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

}

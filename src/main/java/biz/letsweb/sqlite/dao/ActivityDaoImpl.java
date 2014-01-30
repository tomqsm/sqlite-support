package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.ActivitySqls;
import biz.letsweb.sqlite.mvc.model.Activity;
import biz.letsweb.sqlite.mvc.model.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ActivityDaoImpl implements ActivityDao {
  private JdbcTemplate jdbcTemplate;

  public ActivityDaoImpl() {
    jdbcTemplate = new JdbcTemplate(SqliteUtils.getDataSource());
  }

  @Override
  public int save(Activity object) throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int update(Activity object) throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Iterable<Activity> findAll() throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Activity t) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Activity findByName(String entityName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Activity findById(int id) {
    return jdbcTemplate.queryForObject(ActivitySqls.FIND_BY_ID.getSql(), new Object[] {id},
        new RowMapper<Activity>() {
          @Override
          public Activity mapRow(ResultSet rs, int i) throws SQLException {
            Activity activity = new Activity();
            activity.setId(rs.getInt(1));
            activity.setName(rs.getString(2));
            return activity;
          }
        });
  }

}

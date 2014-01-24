package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.StageSqls;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Tomasz
 */
public class StageDaoImpl implements StageDao {

  @Override
  public Stage findByName(String stageName) {
    Stage stage = new Stage();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = SqliteUtils.getDataSource().getConnection();
      stmt = con.prepareStatement(StageSqls.FIND_BY_NAME.getSql());
      stmt.setString(1, stageName);
      rs = stmt.executeQuery();
      stage.setId(rs.getInt(1));
      stage.setName(rs.getString(2));
    } catch (SQLException ex) {
      Logger.getLogger(ProjectDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException ex) {
        Logger.getLogger(ProjectDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return stage;
  }

  @Override
  public void save(Stage stage) throws SQLException {
    String insert =
        "INSERT INTO activities VALUES (null, (SELECT id FROM types WHERE name='stage'), ?);";
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(StageSqls.SAVE.getSql());
    ps.setString(1, stage.getName());
    ps.executeUpdate();
    ps.close();
    con.close();
  }

  @Override
  public void update(Stage stage) throws Exception {
    String update = "UPDATE activities SET name=? WHERE id=?;";
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(update);
    ps.setString(1, stage.getName());
    ps.setInt(2, stage.getId());
    ps.executeUpdate();
    ps.close();
    con.close();
  }

  @Override
  public Stages findAll() throws Exception {
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(StageSqls.FIND_ALL.getSql());
    final ResultSet rs = ps.executeQuery();
    Stages stages = new Stages();
    Stage stage = new Stage();
    while (rs.next()) {
      stage.setId(rs.getInt(1));
      stage.setName(rs.getString(2));
      stages.add(stage);
    }
    return stages;
  }

  @Override
  public Stages findByProject(String projectName) throws SQLException {
    String query =
        "select * from activities a WHERE a.type_id = (SELECT id FROM types WHERE name='stage') AND a.name=?;";
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1, projectName);
    final ResultSet rs = ps.executeQuery();
    Stages stages = new Stages();
    Stage stage = new Stage();
    while (rs.next()) {
      stage.setId(rs.getInt(1));
      stage.setName(rs.getString(2));
      stages.add(stage);
    }
    return stages;
  }

}

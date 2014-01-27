package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.configuration.ProjectSqls;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Tomasz
 */
public final class ProjectDaoImpl implements ProjectDao {

  @Override
  public Project findByName(String projectName) {
    Project p = new Project();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = SqliteUtils.getDataSource().getConnection();
      stmt = con.prepareStatement(ProjectSqls.FIND_BY_NAME.getSql());
      stmt.setString(1, projectName);
      rs = stmt.executeQuery();
      p.setId(rs.getInt(1));
      p.setName(rs.getString(2));
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
    return p;
  }

  @Override
  public void save(Project project) throws SQLException {
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(ProjectSqls.SAVE.getSql());
    ps.setString(1, project.getName());
    ps.executeUpdate();
    ps.close();
    con.close();
  }

  @Override
  public void update(Project project) throws Exception {
    Connection con = SqliteUtils.getDataSource().getConnection();
    PreparedStatement ps = con.prepareStatement(ProjectSqls.UPDATE.getSql());
    ps.setString(1, project.getName());
    ps.setInt(2, project.getId());
    ps.executeUpdate();
    ps.close();
    con.close();
  }

  @Override
    public List<Project> findAll() throws Exception {
        Connection con = SqliteUtils.getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(ProjectSqls.FIND_ALL.getSql());
        final ResultSet rs = ps.executeQuery();
        List<Project> projects = new ArrayList<>();
        Project project = null;
        while (rs.next()) {
            project = new Project();
            project.setId(rs.getInt(1));
            project.setName(rs.getString(2));
            projects.add(project);
        }
        rs.close();
        ps.close();
        con.close();
        return projects;
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
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

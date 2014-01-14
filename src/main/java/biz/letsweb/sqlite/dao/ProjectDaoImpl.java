package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;
import biz.letsweb.sqlite.mvc.model.Stages;
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
public class ProjectDaoImpl implements ProjectDao {

    @Override
    public Project findByName(String projectName) {
//        String query = "select name from activities p where p.name=?;";
        String query = "SELECT a.id, a.name FROM activities a JOIN types t ON a.type_id=t.id WHERE t.id=(SELECT id FROM types WHERE name='project') AND a.name=?;";
        Project p = new Project();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
            stmt = con.prepareStatement(query);
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
        String insert = "INSERT INTO activities VALUES (null, 1, ?);";
        Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, project.getName());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    @Override
    public void update(Project project) throws Exception {
        String update = "UPDATE activities SET name=? WHERE id=?;";
        Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1, project.getName());
        ps.setInt(2, project.getId());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    @Override
    public List<Project> findAll() throws Exception {
        String query = "select * from activities WHERE type_id = (SELECT id FROM types WHERE name='project');";
        Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        final ResultSet rs = ps.executeQuery();
        List<Project> projects = new ArrayList<>();
        Project project = new Project();
        while (rs.next()) {
            project.setId(rs.getInt(1));
            project.setName(rs.getString(2));
            projects.add(project);
        }
        return projects;
    }
}

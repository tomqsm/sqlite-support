package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.mvc.model.Project;
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
public class ProjectDaoImpl implements ProjectDao {

    @Override
    public Project findByName(String projectName) {
//        String query = "select name from activities p where p.name=?;";
        String query = "SELECT a.name FROM activities a JOIN types t ON a.id=t.id WHERE t.id=(SELECT id FROM types WHERE name='project') AND a.name=?;";
        Project p = new Project();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, projectName);
            rs = stmt.executeQuery();
            p.setName(rs.getString("name"));
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

}

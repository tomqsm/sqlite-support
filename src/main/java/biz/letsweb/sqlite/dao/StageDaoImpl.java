package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.TimingSqlite;
import biz.letsweb.sqlite.mvc.model.Project;
import biz.letsweb.sqlite.mvc.model.Stage;
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
        Stage s = new Stage();
        String query = "select name from stages s where s.name=?;";
        Project p = new Project();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, stageName);
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
        return s;
    }

    @Override
    public void save(Stage stage) throws SQLException {
        String insert = "INSERT INTO activities VALUES (null, (SELECT id FROM types WHERE name='stage'), ?);";
        Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, stage.getName());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

}

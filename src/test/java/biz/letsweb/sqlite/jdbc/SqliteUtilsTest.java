package biz.letsweb.sqlite.jdbc;

import biz.letsweb.sqlite.ApplicationException;
import biz.letsweb.sqlite.TimingSqlite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqliteUtilsTest {
    
    private final static Logger LOG = LoggerFactory.getLogger(SqliteUtilsTest.class);
    
    @Test
    public void setupTables() throws SQLException, ApplicationException {
        TimingSqlite timingDb = TimingSqlite.getTimingDbSingleton();
        timingDb.create();
        String sql = "SELECT * FROM projects p WHERE p.id=1;";
        try (final Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
//                Project p = new Project();
////                LOG.info("id: {}", rs.getInt(1));
//                p.setId(rs.getInt(1));
////                LOG.info("name: {}", rs.getString(2));
//                p.setName(rs.getString(2));
////                LOG.info("setCurrent: {}", rs.getInt(3));
//                p.setCurrent(rs.getInt(3));
////                LOG.info("start_time: {}", rs.getLong(4));
//                p.setStartTime(rs.getLong(4));
////                LOG.info("stop_time: {}", rs.getLong(5));
//                p.setStopTime(rs.getLong(5));
////                LOG.info("description: {}", rs.getString(6));
//                p.setDescription(rs.getString(6));
                
//                Task t = new Task();
////                LOG.info("task id: {}", rs.getInt(7));
//                t.setId(rs.getInt(7));
////                LOG.info("project_id: {}", rs.getInt(8));
//                t.setProjectId(rs.getInt(8));
////                LOG.info("task name: {}", rs.getString(9));
//                t.setName(rs.getString(9));
////                LOG.info("task is focused: {}", rs.getInt(10));
//                t.setCurrent(rs.getInt(10));
////                LOG.info("task is_keeping_time: {}", rs.getInt(11));
//                t.isKeepingTime(rs.getInt(11));
////                LOG.info("task start_time: {}", rs.getLong(12));
//                t.setStartTime(rs.getLong(12));
////                LOG.info("task stop_time: {}", rs.getLong(13));  
//                t.setStopTime(rs.getLong(13));
////                LOG.info("task description: {}", rs.getString(14));
//                t.setDescription(rs.getString(14));
//                p.addNewTask(t);
//                p.setFocusedTask(t);
//                LOG.info(p.toString());
//                LOG.info(t.toString());
            }
        }
        sql = "select ";
    }
    
    @Test
    public void testDate() throws SQLException {
        
        String sql = "DROP TABLE IF EXISTS datetest;"
                + "CREATE TABLE datetest ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "change_time DATE,"
                + "last_modified DATETIME"
                + ");";
        try (
                final Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
                final Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }
        
        sql = "INSERT INTO datetest VALUES (null, 1385502209380, datetime('now'));";
        try (
                final Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
                final Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }
        
        sql = "SELECT change_time, last_modified FROM datetest where id=1;";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try (final Connection con = TimingSqlite.getTimingDbSingleton().getDataSource().getConnection();
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery(sql);) {
//            Date date = df.parse(rs.getString("change_time"));
            System.out.println("changetime: " + new Date(rs.getLong("change_time")));
            System.out.println("lastmodified: " + rs.getString("last_modified"));
            LOG.info("df{}", 12);
        }
    }
}
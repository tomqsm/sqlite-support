package biz.letsweb.sqlite.jdbc;

import biz.letsweb.sqlite.TimingDb;
import biz.letsweb.sqlite.SqlSupportApi;
import static biz.letsweb.sqlite.SqlSupportApi.SQL_CREATE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
import org.junit.Test;

public class SqliteUtilsTest {

    public SqliteUtilsTest() {
    }

    @Test
    public void setupTables() {
        TimingDb timingDb = new TimingDb();
        timingDb.create();
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
                final Connection con = TimingDb.getDataSource().getConnection();
                final Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }

        sql = "INSERT INTO datetest VALUES (null, 1385502209380, datetime('now'));";
        try (
                final Connection con = TimingDb.getDataSource().getConnection();
                final Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }

        sql = "SELECT change_time, last_modified FROM datetest where id=1;";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try (final Connection con = TimingDb.getDataSource().getConnection();
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery(sql);) {
//            Date date = df.parse(rs.getString("change_time"));
            System.out.println("changetime: " + new Date(rs.getLong("change_time")));
            System.out.println("lastmodified: " + rs.getString("last_modified"));
        }
    }
}
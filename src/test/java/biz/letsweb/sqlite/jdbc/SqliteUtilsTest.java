package biz.letsweb.sqlite.jdbc;

import biz.letsweb.sqlite.SqliteUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqliteUtilsTest {

    private final static Logger LOG = LoggerFactory.getLogger(SqliteUtilsTest.class);

    @BeforeClass
    public static void setUpClass() {
        SqliteUtils.drop("activities_types", "activities", "types");
    }

    @Test
    public void testDate() throws SQLException, ParseException {

        String sql = "DROP TABLE IF EXISTS datetest;"
                + "CREATE TABLE datetest ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "change_time DATE,"
                + "last_modified DATETIME"
                + ");";
        try (
                final Connection con = SqliteUtils.getDataSource().getConnection();
                final Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }

        sql = "INSERT INTO datetest VALUES (null, 1385502209380, datetime('now'));";
        try (
                final Connection con = SqliteUtils.getDataSource().getConnection();
                final Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }

        sql = "SELECT change_time, last_modified FROM datetest where id=1;";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (final Connection con = SqliteUtils.getDataSource().getConnection();
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery(sql);) {
            Date lastModifiedDate = df.parse(rs.getString("last_modified"));
            System.out.println("changetime: " + new Date(rs.getLong("change_time")));
            System.out.println("lastmodified: " + lastModifiedDate);
        }
    }
}

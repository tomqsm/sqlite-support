package biz.letsweb.sqlite.jdbc;

import biz.letsweb.sqlite.SqliteUtils;
import biz.letsweb.sqlite.FulljarRuntimeException;
import biz.letsweb.sqlite.ScriptAction;
import java.io.File;
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
import org.junit.Test;
import static org.junit.Assert.*;

public class SqliteUtilsTest {

    public SqliteUtilsTest() {
    }

    @Test
    public void testLoadSqliteDriver() {
        System.out.println("loadSqliteDriver");
        assertNotNull(SqliteUtils.getDataSource(ScriptAction.CREATE.getSqlFile()));
    }

    @Test
    public void setupTables() {
        final String sql = SqliteUtils.loadSql(new File("src/main/resources/sql/create.sql"));
        SqliteUtils.setupTables(sql);
    }

    @Test
    public void testDate() {
        String sql = "SELECT change_time, last_modified FROM datetest where id=1;";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try (final Connection con = SqliteUtils
                .getDataSource(new File("timing.db"))
                .getConnection();
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery(sql);
                ) {
                Date date = df.parse(rs.getString("change_time"));
            System.out.println(date);
            System.out.println(rs.getString("last_modified"));
        } catch (final SQLException ex) {
            throw new FulljarRuntimeException(String.format("Sql %s could not be executed: %s.", sql, ex.getMessage()), ex);
        } catch (ParseException ex) {
            Logger.getLogger(SqliteUtilsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
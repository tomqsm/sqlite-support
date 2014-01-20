package biz.letsweb.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

public class TimingSqlite {

    private final static Logger LOG = LoggerFactory.getLogger(TimingSqlite.class);
    private static SQLiteConnectionPoolDataSource DATA_SOURCE = null;
    private static File TIMING_DB_FILE = new File("timing.sqlite");
    private static File SQL_CREATE = new File("src/main/resources/sql/timingdb/dev_create.sql");
    private File SQL_DROP = new File("src/main/resources/sql/timingdb/dev_drop.sql");
    private static TimingSqlite timingDb = null;

    private TimingSqlite() {
    }

    public static TimingSqlite getTimingDbSingleton() {
        if (timingDb == null) {
            timingDb = new TimingSqlite();
        }
        return timingDb;
    }

    public DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            final SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding("UTF-8"));
            config.setDateClass("INTEGER");
//            config.setDateStringFormat("yyyy-MM-dd HH:mm");
            DATA_SOURCE = new SQLiteConnectionPoolDataSource();
            DATA_SOURCE.setUrl("jdbc:sqlite:" + TIMING_DB_FILE.getName());
            DATA_SOURCE.setConfig(config);
            LOG.trace("Using a new data source.");
            return DATA_SOURCE;
        } else {
            LOG.trace("Using an existing data source.");
            return DATA_SOURCE;
        }
    }

    /**
     * Loads sql script for setting up a data base.
     *
     * @return
     */
    public String loadSql(File sqlFile) {
        LOG.trace("Loading sql script.");
        String sql = null;
        try (InputStream is = new FileInputStream(sqlFile.getAbsolutePath());
                StringWriter writer = new StringWriter()) {
            IOUtils.copy(is, writer, "UTF-8");
            sql = writer.toString();
            LOG.info(sql);
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't load sql script. ", ex);
        }
        return sql;
    }

    public void drop(final String... tables) {
        LOG.trace("Setting up tables.");
        try (final Connection connection = getDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            for (String table : tables) {
                statement.execute("DROP TABLE IF EXISTS " + table + ";");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't drop tables. " + e.getMessage(), e);
        }
    }

    public void create() {
        LOG.trace("Setting up tables.");
        try (final Connection connection = getDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(loadSql(SQL_CREATE));
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't setup tables. " + e.getMessage(), e);
        }
    }

    public void delete() {
        if (TIMING_DB_FILE.exists()) {
            TIMING_DB_FILE.delete();
        }
    }
}

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

public class SqliteUtils {

    private final static Logger LOG = LoggerFactory.getLogger(SqliteUtils.class);
    private static SQLiteConnectionPoolDataSource DATA_SOURCE = null;

    public static synchronized DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            final SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding("UTF-8"));
            config.setDateClass("INTEGER");
            config.setDateStringFormat("yyyy-MM-dd HH:mm");
            DATA_SOURCE = new SQLiteConnectionPoolDataSource();
            DATA_SOURCE.setUrl("jdbc:sqlite:timing.db");
            DATA_SOURCE.setConfig(config);
            LOG.trace("Using a new data source.");
            return DATA_SOURCE;
        } else {
            LOG.trace("Using an existing data source.");
            return DATA_SOURCE;
        }
    }

    public static synchronized DataSource getDataSource(File dbFile) {
        if (DATA_SOURCE == null) {
            final SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding("UTF-8"));
            config.setDateClass("INTEGER");
            config.setDateStringFormat("yyyy-MM-dd HH:mm");
            DATA_SOURCE = new SQLiteConnectionPoolDataSource();
            DATA_SOURCE.setUrl("jdbc:sqlite:" + dbFile.getName());
            DATA_SOURCE.setConfig(config);
            LOG.trace("Using a new data source.");
            return DATA_SOURCE;
        } else {
            LOG.trace("Using an existing data source.");
            return DATA_SOURCE;
        }
    }

    public static void setupTables(String sqlString) {
        LOG.trace("Setting up tables.");
        try (final Connection connection = getDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            throw new FulljarRuntimeException("Couldn't setup tables. " + e.getMessage(), e);
        }
    }

    /**
     * Loads sql script for setting up a data base.
     *
     * @return
     */
    public static String loadSql(File sqlFile) {
        LOG.trace("Loading sql script.");
        String sql = null;
        try (InputStream is = new FileInputStream(sqlFile.getAbsolutePath());
                StringWriter writer = new StringWriter()) {
            IOUtils.copy(is, writer, CommonsConfig.APP_PROPS.getString("encoding"));
            sql = writer.toString();
            LOG.info(sql);
        } catch (IOException ex) {
            throw new FulljarRuntimeException("Couldn't load sql script. ", ex);
        }
        return sql;
    }
}

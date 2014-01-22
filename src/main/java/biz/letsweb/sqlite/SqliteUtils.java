package biz.letsweb.sqlite;

import biz.letsweb.sqlite.configuration.Configuration;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

public class SqliteUtils {

    private final static Logger LOG = LoggerFactory.getLogger(SqliteUtils.class);
    private static SQLiteConnectionPoolDataSource DATA_SOURCE = null;
    private static File DB_FILE = new File(Configuration.DB_FILE_NAME.toString());
    private static SqliteUtils timingDb = null;

    private SqliteUtils() {

    }

    public static DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            final SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding("UTF-8"));
            config.setDateClass("INTEGER");
//            config.setDateStringFormat("yyyy-MM-dd HH:mm");
            DATA_SOURCE = new SQLiteConnectionPoolDataSource();
            DATA_SOURCE.setUrl("jdbc:sqlite:" + DB_FILE);
            DATA_SOURCE.setConfig(config);
            LOG.trace("Using a new data source.");
            return DATA_SOURCE;
        } else {
            LOG.trace("Using an existing data source.");
            return DATA_SOURCE;
        }
    }

    public static void drop(final String... tables) {
        try (final Connection connection = getDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            for (String table : tables) {
                LOG.trace("Dropping {}", table);
                statement.execute(String.format(Configuration.DROP_TABLE_IF_EXISTS.toString(), table));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't drop tables. " + e.getMessage(), e);
        }
    }

    public static void create() {
        try (final Connection connection = getDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            LOG.trace("Creating tables.");
            statement.executeUpdate(Configuration.CREATE_TABLES_SQL.toString());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't setup tables. " + e.getMessage(), e);
        }
    }
}

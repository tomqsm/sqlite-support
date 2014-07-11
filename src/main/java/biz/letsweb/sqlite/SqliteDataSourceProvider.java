package biz.letsweb.sqlite;

import biz.letsweb.sqlite.configuration.Configuration;
import java.io.File;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

public class SqliteDataSourceProvider implements DataSourceProvidable{

    private final static Logger LOG = LoggerFactory.getLogger(SqliteDataSourceProvider.class);
    private static SQLiteConnectionPoolDataSource DATA_SOURCE = null;
    private static File DB_FILE = new File(Configuration.DB_FILE_NAME.toString());

    private SqliteDataSourceProvider() {
    }

    public synchronized static DataSource createDataSource() {
        if (DATA_SOURCE == null) {
            final SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            config.setEncoding(SQLiteConfig.Encoding.getEncoding("UTF-8"));
            config.setDateClass("INTEGER");
            // config.setDateStringFormat("yyyy-MM-dd HH:mm");
            DATA_SOURCE = new SQLiteConnectionPoolDataSource();
            DATA_SOURCE.setUrl("jdbc:sqlite:" + DB_FILE);
            DATA_SOURCE.setConfig(config);
            LOG.trace("Using a new data source.");
        } else {
            LOG.trace("Using an existing data source.");
        }
        return DATA_SOURCE;
    }

    @Override
    public DataSource getDataSource() {
        return createDataSource();
    }
}

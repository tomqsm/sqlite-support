package biz.letsweb.sqlite;

import biz.letsweb.sqlite.configuration.Configuration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author toks
 */
public class DbConstructor {

    private final static Logger LOG = LoggerFactory.getLogger(DbConstructor.class);
    private DataSourceProvidable dataSourceProvidable;
    
    public DbConstructor() {
    }
    

    public void drop(final String... tables) {
        try (final Connection connection = dataSourceProvidable.getDataSource().getConnection();
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

    public void create() {
        try (final Connection connection = dataSourceProvidable.getDataSource().getConnection();
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            LOG.trace("Creating tables.");
            statement.executeUpdate(Configuration.CREATE_TABLES_SQL.toString());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't setup tables. " + e.getMessage(), e);
        }
    }

    public void setDataSourceProvidable(DataSourceProvidable dataSourceProvidable) {
        this.dataSourceProvidable = dataSourceProvidable;
    }
    
}

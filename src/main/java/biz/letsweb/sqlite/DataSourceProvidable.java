package biz.letsweb.sqlite;

import javax.sql.DataSource;

/**
 *
 * @author toks
 */
public interface DataSourceProvidable {
    DataSource getDataSource();
}

package biz.letsweb.sqlite;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File sqlFile = new File("sql/create.sql");
        if (sqlFile.exists()) {
            final String sql = SqliteUtils.loadSql(sqlFile);
            SqliteUtils.setupTables(sql);
        } else {
            LOG.error("no file found");
        }
    }
}

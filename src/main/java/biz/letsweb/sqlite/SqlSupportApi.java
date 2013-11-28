package biz.letsweb.sqlite;

import java.io.File;

public interface SqlSupportApi {

    File TIMING_DB_FILE = new File("timing.db");
    File SQL_CREATE = new File("src/main/resources/sql/timingdb/create.sql");

    void create();
    void delete();
}

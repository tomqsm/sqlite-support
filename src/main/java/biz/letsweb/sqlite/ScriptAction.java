package biz.letsweb.sqlite;

import java.io.File;

public enum ScriptAction {

    CLEAR(new File("sql/clear.sql")),
    CREATE(new File("sql/create.sql")),
    DROP(new File("sql/drop.sql")),
    INSERT(new File("sql/insert.sql")),
    UPDATE(new File("sql/update.sql"));
    File sqlFile;

    private ScriptAction(File f) {
        sqlFile = f;
    }

    public File getSqlFile() {
        return sqlFile;
    }
}

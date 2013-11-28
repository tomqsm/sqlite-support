package biz.letsweb.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tomasz
 */
public class TimeProjectTaskTable {

    private static final String createTableSql = "DROP TABLE IF EXISTS datetest;"
            + "CREATE TABLE datetest ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "change_time DATE,"
            + "last_modified DATETIME"
            + ");";
    private static final String dropTableSql = "DROP TABLE IF EXISTS datetest;";

    public void createTable() throws SQLException {
        try (
                Connection con = TimingDb.getDataSource().getConnection();
                Statement statement = con.createStatement();) {
            statement.executeUpdate(createTableSql);
        }
    }

    public void dropTable() throws SQLException {
        try (
                Connection con = TimingDb.getDataSource().getConnection();
                Statement statement = con.createStatement();) {
            statement.executeUpdate(dropTableSql);
        }
    }

    public void appendRow() {
    }

    public void removeRowAt() {
    }
}

package biz.letsweb.sqlite;

import biz.letsweb.sqlite.dao.ProjectDaoImpl;
import biz.letsweb.sqlite.mvc.model.Project;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqliteSupportMain {

    private static final Logger LOG = LoggerFactory.getLogger(SqliteSupportMain.class);

    public static void main(String... args) throws Exception {
        LOG.info("Main started");
        Scanner in = new Scanner(System.in);
        boolean run = true;
        System.out.println("type your command ...");
        SqliteUtils.drop("activities_types", "activities", "types");
        SqliteUtils.create();
        while (run) {
            String command = in.nextLine();
            if (command.equalsIgnoreCase("projects")) {
                final List<Project> projects = new ProjectDaoImpl().findAll();
                for (Project p : projects) {
                    System.out.println(p.getId() + ")" + p.getName());
                }
            }
            if (command.equals("stages")) {
                System.out.println("stages: 4");
            }
            if (command.equals("exit")) {
                run = false;
            }
        }
    }
}

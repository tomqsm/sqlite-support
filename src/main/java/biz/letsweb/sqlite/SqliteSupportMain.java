package biz.letsweb.sqlite;

import biz.letsweb.sqlite.configuration.Configuration;
import biz.letsweb.sqlite.dao.ProjectDao;
import biz.letsweb.sqlite.dao.ProjectSqliteDaoImpl;
import biz.letsweb.sqlite.mvc.model.Project;
import java.io.File;
import java.util.Scanner;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqliteSupportMain {

    private static final Logger LOG = LoggerFactory.getLogger(SqliteSupportMain.class);
    private ProjectDao projectDao;
    private DbConstructor dbConstructor;

    public static void main(String... args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        SqliteSupportMain main = (SqliteSupportMain) ctx.getBean(SqliteSupportMain.class);
        PropertyConfigurator.configure("./config/log4j.properties");
        LOG.info("Main started");
        LOG.trace("Main started");
        main.start(args);

    }

    private void start(String[] args) throws Exception {
        File f = new File(Configuration.DB_FILE_NAME.toString());
        if (!f.exists()) {
            dbConstructor.create();
        }
        Scanner in = new Scanner(System.in);
        boolean run = true;
        System.out.println("type your command ...");
        while (run) {
            String command = in.nextLine();
            if (command.equalsIgnoreCase("projects")) {
                for (Project p : projectDao.findAll()) {
                    System.out.println(p.getId() + ")" + p.getName());
                }
            }
            if (command.startsWith("add project")) {
                final String[] splited = StringUtils.split(command);
                Project newProject = new Project();
                newProject.setName(splited[splited.length - 1]);
                projectDao.save(newProject);
                System.out.println("Project " + newProject.getName() + " has been added.");
            }
            if (command.startsWith("delete project")) {
                final String[] splited = StringUtils.split(command);
                Project newProject = projectDao.findByName(splited[splited.length - 1]);
                projectDao.deleteById(newProject.getId());
            }
            if (command.equals("present")) {
            }
            if (command.equals("stages")) {
                System.out.println("stages: 4");
            }
            if (command.equals("exit")) {
                run = false;
            }
        }
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public void setDbConstructor(DbConstructor dbConstructor) {
        this.dbConstructor = dbConstructor;
    }

}

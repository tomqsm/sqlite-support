package biz.letsweb.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SqliteSupportMain {
    private static final Logger LOG = LoggerFactory.getLogger(SqliteSupportMain.class);
    public static void main(String ... args){
        LOG.info("Main started");
        String command = args[0];
        if(command.equalsIgnoreCase("showProjects")){
        }
    }
}

package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

/**
 * 
 * @author toks
 */
public enum StorySqls implements Sql {
  FIND_TASKS {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/story/findTasks");
    }
  },
  FIND_BY_NAME {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/story/findByName");
    }
  };
  static {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    XML_CONFIG = configProvider.getXMLConfiguration();
  }
  private static XMLConfiguration XML_CONFIG;
}

package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

public enum ProjectSqls implements Sql {
  FIND_ALL {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/project/findAll");
    }
  },
  FIND_BY_NAME {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/project/findByName");
    }
  },
  SAVE {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/project/save");
    }
  },
  UPDATE {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/project/update");
    }
  };
  static {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    XML_CONFIG = configProvider.getXMLConfiguration();
  }
  private static XMLConfiguration XML_CONFIG;

}

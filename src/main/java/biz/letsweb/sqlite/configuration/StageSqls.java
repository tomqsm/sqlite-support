package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

public enum StageSqls implements Sql {
  FIND_ALL {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/stage/findAll");
    }
  },
  FIND_BY_NAME {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/stage/findByName");
    }
  },
  SAVE {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/stage/save");
    }
  },
  UPDATE {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/stage/update");
    }
  };
  static {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    XML_CONFIG = configProvider.getXMLConfiguration();
  }
  private static XMLConfiguration XML_CONFIG;

}

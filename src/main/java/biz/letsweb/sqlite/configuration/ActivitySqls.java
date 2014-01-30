package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

public enum ActivitySqls implements SqlStringProvidable {
  FIND_BY_ID {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/activity/findById");
    }
  };
  private static XMLConfiguration XML_CONFIG = Configuration.XML_CONFIG;

}

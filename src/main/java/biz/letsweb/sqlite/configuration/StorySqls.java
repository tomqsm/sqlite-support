package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

/**
 * 
 * @author toks
 */
public enum StorySqls implements SqlStringProvidable {
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

  private static XMLConfiguration XML_CONFIG = Configuration.XML_CONFIG;
}

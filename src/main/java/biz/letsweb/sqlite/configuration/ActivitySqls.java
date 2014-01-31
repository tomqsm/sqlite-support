package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

public enum ActivitySqls implements SqlStringProvidable {
  FIND_BY_ID {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/activity/findById");
    }
  },
  FIND_SUPER_ASSOCIATION_BY_SUB_ID {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/activity/findSuperAssociationBySubId");
    }
  },
  FIND_RECENT_BY_PROJECT_ID {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/activity/findRecentByProjectId");
    }
  },
  FIND_RECENT_SUB_ACTIVITY {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/activity/findRecentSubActivity");
    }
  },
  FIND_RECENT_ACTIVITY {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/activity/findRecentActivity");
    }
  };
  private static XMLConfiguration XML_CONFIG = Configuration.XML_CONFIG;

}

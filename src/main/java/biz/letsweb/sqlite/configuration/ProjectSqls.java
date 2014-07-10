package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.XMLConfiguration;

public enum ProjectSqls implements SqlStringProvidable {
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
  },
  DELETE {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/project/delete");
    }
  },
  ASSOCIATE_STAGE {

    @Override
    public String getSql() {
      return XML_CONFIG.getString("sql/project/associateStage");
    }
  };
  private static XMLConfiguration XML_CONFIG = Configuration.XML_CONFIG;

}

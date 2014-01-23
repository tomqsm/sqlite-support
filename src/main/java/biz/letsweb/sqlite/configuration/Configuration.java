package biz.letsweb.sqlite.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FileUtils;

/**
 * 
 * @author Tomasz
 */
public enum Configuration {

  COLOR {
    @Override
    public String toString() {
      HierarchicalConfiguration hc = XML_CONFIG.configurationsAt("properties").get(FIRST);
      return hc.getString(super.name().toLowerCase());
    }

    @Override
    public void setValue(final String value) throws ConfigurationException {
      XML_CONFIG.setProperty("properties/color", value);
      XML_CONFIG.save();
    }

    @Override
    public List<Object> getValues() throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
      // methods, choose Tools |
      // Templates.
    }
  },
  TIME {
    @Override
    public String toString() {
      HierarchicalConfiguration hc = XML_CONFIG.configurationsAt("properties").get(FIRST);
      return hc.getString(super.name().toLowerCase());
    }

    @Override
    public void setValue(String value) throws ConfigurationException {
      XML_CONFIG.setProperty("properties/time", value);
      XML_CONFIG.save();
    }

    @Override
    public List<Object> getValues() throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
      // methods, choose Tools |
      // Templates.
    }

  },
  CREATE_TABLES_SQL {
    @Override
    public String toString() {
      final String fileName = XML_CONFIG.getString("sql/fromFile/createTablesSql");
      File f = new File(fileName);
      String createSql = "";
      try {
        createSql = FileUtils.readFileToString(f, Charset.forName("UTF-8"));
      } catch (IOException ex) {
        throw new RuntimeException("Problem reading file. " + ex.getMessage(), ex);
      }
      return createSql;
    }

    @Override
    public void setValue(String value) throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getValues() throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  },
  DROP_TABLE_IF_EXISTS {
    @Override
    public String toString() {
      return XML_CONFIG.getString("sql/dropTableIfExists");
    }

    @Override
    public void setValue(String value) throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getValues() throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
      // methods, choose Tools |
      // Templates.
    }

  },
  DB_FILE_NAME {
    @Override
    public String toString() {
      return XML_CONFIG.getString("properties/dbFileName");
    }

    @Override
    public void setValue(String value) throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getValues() throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

  },
  TABLE_NAMES {

    @Override
    public void setValue(String value) throws ConfigurationException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getValues() throws ConfigurationException {
      return XML_CONFIG.getList("sql/tables/table");
    }

  };

  static {
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.initialiseXMLConfiguration("config/configuration.xml");
    XML_CONFIG = configProvider.getXMLConfiguration();
  }
  private static XMLConfiguration XML_CONFIG;
  private static final int FIRST = 0;

  public abstract void setValue(final String value) throws ConfigurationException;

  public abstract List<Object> getValues() throws ConfigurationException;
}

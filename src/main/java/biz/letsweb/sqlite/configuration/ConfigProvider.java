package biz.letsweb.sqlite.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Tomasz
 */
public final class ConfigProvider {

  private final Logger LOG = LoggerFactory.getLogger(ConfigProvider.class);

  private XMLConfiguration config;

  public ConfigProvider() {}

  public final void initialiseXMLConfiguration(String fileName) {
    try {
      config = new XMLConfiguration();
      config.setDelimiterParsingDisabled(true);
      // config.setAttributeSplittingDisabled(true);
      config.setExpressionEngine(new XPathExpressionEngine());
      config.setAutoSave(true);
      config.load(fileName);
    } catch (ConfigurationException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

  public XMLConfiguration getXMLConfiguration() {
    return config;
  }
}

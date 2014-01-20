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
public class ConfigProvider {

    private final Logger LOG = LoggerFactory.getLogger(ConfigProvider.class);

    private XMLConfiguration config;

    public ConfigProvider() {
    }

    public final void initialiseXMLConfiguration(String fileName) {
        try {
            config = new XMLConfiguration(fileName);
            config.setExpressionEngine(new XPathExpressionEngine());
        } catch (ConfigurationException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public XMLConfiguration getXMLConfiguration() {
        return config;
    }
}

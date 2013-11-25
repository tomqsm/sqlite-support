package biz.letsweb.sqlite;

import java.io.File;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonsConfig {

    private static Logger LOG = LoggerFactory.getLogger(CommonsConfig.class);
    public static final PropertiesConfiguration APP_PROPS;
    public PropertiesConfiguration projectProps;

    static {
        APP_PROPS = new PropertiesConfiguration();
        APP_PROPS.setFileName("application.properties");
        try {
            APP_PROPS.load();
        } catch (ConfigurationException ex) {
            throw new FulljarRuntimeException("Couldn't construct commons properties. ", ex);
        }
    }

    public CommonsConfig(File projProps) {
        Validate.notNull(projProps, "File poperty has not been passed to commons config. ");
        try {
            LOG.trace("Preparing properties: {}" + projProps.getAbsolutePath());
            projectProps = new PropertiesConfiguration(projProps);
        } catch (ConfigurationException ex) {
            throw new FulljarRuntimeException("Couldn't load project propeerties. " + ex.getMessage(), ex);
        }
    }

    public PropertiesConfiguration getProjectProps() {
        return projectProps;
    }
    
}

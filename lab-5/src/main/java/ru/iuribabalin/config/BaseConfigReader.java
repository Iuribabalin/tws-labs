package ru.iuribabalin.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseConfigReader {
    private final Properties properties;

    public BaseConfigReader() {
        this.properties = new Properties();
        loadConfig();
    }

    private void loadConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("base.conf")) {
            if (input == null) {
                throw new IOException("Unable to find base.conf");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getSchemas() {
        return properties.getProperty("flyway.schemas");
    }

    public String getLocations() {
        return properties.getProperty("flyway.locations");
    }
}

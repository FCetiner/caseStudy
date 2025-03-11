package utils.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private Properties properties;
    private static final String PROPERTY_FILE_PATH = "src/main/resources/configuration.properties";

    private PropertyManager() {
        loadData();
    }

    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
            }
        }
        return instance;
    }

    private void loadData() {
        properties = new Properties();
        try (FileInputStream stream = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Configuration file loading failed: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getEnvironment() {
        return properties.getProperty("environment", "preprod");
    }
}

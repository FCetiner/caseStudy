package utils.propertyManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private String environment;
    private static final String PROPERTY_FILE_PATH = System.getProperty("user.dir") + "/configuration.properties";

    /**
     * Returns the singleton instance of PropertyManager.
     *
     * @return PropertyManager instance
     */
    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }

    /**
     * Loads property values from the configuration file.
     */
    private void loadData() {
        Properties prop = new Properties();

        try (FileInputStream stream = new FileInputStream(PROPERTY_FILE_PATH)) {
            prop.load(stream);
        } catch (IOException error) {
            try {
                throw new IOException(error);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        environment = prop.getProperty("environment");
    }

    /**
     * Gets the application path.
     *
     * @return the environment
     */
    public String environmentVariable() {
        return environment;
    }
}
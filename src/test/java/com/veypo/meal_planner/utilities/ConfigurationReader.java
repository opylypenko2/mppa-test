package com.veypo.meal_planner.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * reads two different properties files: configuration.properties and configuration-local.properties
 */

public class ConfigurationReader {

    private static final String DEFAULT_PROPERTIES_FILE = "configuration.properties";
    private static final String ADDITIONAL_PROPERTIES_FILE = "configuration-local.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            // Load default properties file
            FileInputStream inputStream = new FileInputStream(DEFAULT_PROPERTIES_FILE);
            // FileInputStream(fileName) --> Creates a FileInputStream by opening a
            // connection to an actual file, the file named by the path name `name`
            // in the file system.
            properties.load(inputStream);
            // load(input) --> Reads a property list (key and element pairs) from the input byte stream.
            inputStream.close();
            // close() --> Closes this file input stream and releases
            // any system resources associated with the stream.

            // Load additional properties file
            inputStream = new FileInputStream(ADDITIONAL_PROPERTIES_FILE);
            properties.load(inputStream);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format(
                    "Unable to load '%s' property value", key));
        }

        return value;
    }
}


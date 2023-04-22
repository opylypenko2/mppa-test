package com.veypo.meal_planner.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * reads two different properties files: configuration.properties and configuration-local.properties
 */

public class ConfigurationReader {

    private static final String PROPERTIES_FILE_DEFAULT = "configuration.properties";
    private static final String PROPERTIES_FILE_LOCAL = "configuration-local.properties";
    private static Properties properties;

    static {
        properties = new Properties();

        try {
            // ***** Load default properties file
            // FileInputStream(fileName) --> Creates a FileInputStream by opening a
            // connection to an actual file, the file named by the path name `name`
            // in the file system.
            FileInputStream defaultStream = new FileInputStream(PROPERTIES_FILE_DEFAULT);
            try {
                // load(input) --> Reads a property list (key and element pairs) from the input byte stream.
                properties.load(defaultStream);
            } finally {
                // close() --> Closes this file input stream and releases
                // any system resources associated with the stream.
                defaultStream.close();
            }

            // ***** Load additional properties file
            try (FileInputStream localStream = new FileInputStream(PROPERTIES_FILE_LOCAL)) {
                properties.load(localStream);
            }
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

package com.veypo.meal_planner.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * reads the properties file configuration.properties
 */
public class ConfigurationReader {
    private static Properties properties;

    static {
        properties = new Properties();

        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyName) {

        return properties.getProperty(keyName);
    }
}

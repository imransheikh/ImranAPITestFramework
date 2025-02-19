package com.apar.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Optional: Get property with a default value
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
